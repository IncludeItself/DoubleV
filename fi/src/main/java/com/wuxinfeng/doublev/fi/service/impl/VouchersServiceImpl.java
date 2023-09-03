package com.wuxinfeng.doublev.fi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuxinfeng.common.jwt.JwtService;
import com.wuxinfeng.common.to.FiVoucherReturnTo;
import com.wuxinfeng.common.to.FiVoucherTo;
import com.wuxinfeng.common.utils.PageUtils;
import com.wuxinfeng.common.utils.Query;
import com.wuxinfeng.doublev.fi.dao.VouchersDao;
import com.wuxinfeng.doublev.fi.entity.EntriesEntity;
import com.wuxinfeng.doublev.fi.entity.VouchersEntity;
import com.wuxinfeng.doublev.fi.exception.DuplicateWriteOffException;
import com.wuxinfeng.doublev.fi.exception.WriteOffCleardException;
import com.wuxinfeng.doublev.fi.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("vouchersService")
public class VouchersServiceImpl extends ServiceImpl<VouchersDao, VouchersEntity> implements VouchersService {

    private final RedissonClient redissonClient;
    private final EntriesService entriesService;
    private final JwtService jwtService = new JwtService();

    @RabbitListener(queues = "fi.voucher.reverse.queue")
    @Transactional
    public void delete(Message message, Long code) {
        System.out.println("message：" + message);
        System.out.println("body:" + message.getBody());
        System.out.println("监听到了Code：" + code);
        try {
            baseMapper.deleteByCode(code);
            entriesService.deleteByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VouchersEntity> page = this.page(
                new Query<VouchersEntity>().getPage(params),
                new QueryWrapper<VouchersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public long book(VouchersEntity voucher, Map<String, String> headers) {
        String creator = jwtService.extractUsername(headers);
        voucher.setCreator(creator);
        return book(voucher);
    }

    private long book(VouchersEntity voucher) {
        Date currentDate = new Date(System.currentTimeMillis());
        voucher.setLastChange(currentDate);
        voucher.setTimeCreated(currentDate);
        System.out.println("voucher:" + voucher);
        baseMapper.insert(voucher);
        Long voucherNumber = voucher.getVoucherNumber();
        List<EntriesEntity> entries = voucher.getEntries();
        int i = 0;
        for (EntriesEntity entry : entries) {
            entry.setVoucherNumber(voucherNumber);
            entry.setItemNumber(i++);
        }
        entriesService.saveBatch(entries);
        return voucherNumber;
    }

    @Override
    public long writeOff(long voucherNumber, Map<String, String> headers) throws WriteOffCleardException, DuplicateWriteOffException {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(Long.toString(voucherNumber));
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        if (!StringUtils.isBlank(baseMapper.getWriteOffVoucherByVoucherNumber(voucherNumber))) {
            rLock.unlock();
            throw new DuplicateWriteOffException();
        }
        VouchersEntity voucher = baseMapper.selectById(voucherNumber);
        voucher.setVoucherNumber(null);
        List<EntriesEntity> entriesEntities = entriesService.selectByVoucherNumber(voucherNumber);
        for (EntriesEntity entry : entriesEntities) {
            if (entry.getClearVoucher() != null) {
                rLock.unlock();
                throw new WriteOffCleardException();
            }
            entry.setId(null);
            entry.setClearVoucher(voucherNumber);
            entry.setAmountBusiness(-entry.getAmountBusiness());
            entry.setAmountLocal(-entry.getAmountLocal());
        }
        voucher.setEntries(entriesEntities);
        voucher.setWrittenOffVoucher(voucherNumber);
        long book = book(voucher, headers);
        baseMapper.addWrittenVoucher(voucherNumber, book);
        entriesService.addWittenVoucher(voucherNumber, book);
        rLock.unlock();
        return book;
    }

    @Override
    public boolean isWrittenOff(long integer) {
        if (StringUtils.isBlank(baseMapper.getWriteOffVoucherByVoucherNumber(integer))) return false;
        return true;
    }

    @Override
    @Transactional
    public FiVoucherReturnTo book(FiVoucherTo voucherTo) {
//        System.out.println("voucherTo大小:" + voucherTo.getEntries().size());
//        String companyCode = plantInfoService.getById(voucherTo.getPlant()).getCompanyCode();
//        Date currentDate = new Date(System.currentTimeMillis());
//
//        VouchersEntity vouchersEntity = new VouchersEntity();
//        BeanUtils.copyProperties(voucherTo, vouchersEntity);
//        vouchersEntity.setCompanyCode(companyCode);
//        vouchersEntity.setTimeCreated(currentDate);
//        vouchersEntity.setLastChange(currentDate);
//
//        List<EntriesTo> entries = voucherTo.getEntries();
//        String localCurrency = companyInfoService.getById(companyCode).getCurrency();
//        ArrayList<EntriesEntity> entriesEntities = new ArrayList<EntriesEntity>();
//        ArrayList<FiVoucherReturnItem> fiVoucherReturnItemList = new ArrayList<>();
//        System.out.println("entries.size()：" + entries.size());
//
//        int itemNo = 1;
//        int i = 0;
//        while(i<entries.size()){
//            float sumAmount = 0;
//            EntriesTo entryTo = entries.get(i);
//            EntriesEntity entryEntityGood = new EntriesEntity(null, null, null, localCurrency, null, postKeyDebit, accountDebit, null, localCurrency, null, null, null, null, entryTo.getText(), null, null, null, null, null, null, null, null, null, entryTo.getMaterialCode(), entryTo.getMaterialQuantity(), entryTo.getMaterialUnit(), null);
//            FiVoucherReturnItem fiVoucherReturnItem = new FiVoucherReturnItem(entryTo.getItemNo(), null);
//            do {
//                entryTo = entries.get(i);
//                String currencyBusiness = entryTo.getCurrencyBusiness();
//                Float amountBusiness = entryTo.getAmountBusiness();
//                float amountLocal = currencyRateService.getCurrencyRate(localCurrency, currencyBusiness) * amountBusiness;
//                EntriesEntity entryEntity = new EntriesEntity(null, null, itemNo++, currencyBusiness, null, postKeyCredit, accountCredit, amountBusiness, localCurrency, amountLocal, null, null, null, entryTo.getText(), null, null, null, null, null, null, null, null, null, entryTo.getMaterialCode(), entryTo.getMaterialQuantity(), entryTo.getMaterialUnit(), null);
//                entriesEntities.add(entryEntity);
//                sumAmount += amountLocal;
//                i++;
//            } while (i < entries.size() && entryTo.getItemNo() == entries.get(i).getItemNo());
//            entryEntityGood.setAmountLocal(sumAmount);
//            entryEntityGood.setAmountBusiness(sumAmount);
//            entryEntityGood.setItemNumber(itemNo++);
//            entriesEntities.add(entryEntityGood);
//            fiVoucherReturnItem.setAmount(sumAmount);
//            fiVoucherReturnItemList.add(fiVoucherReturnItem);
//        }
//        System.out.println("entriesEntities："+entriesEntities);
//        vouchersEntity.setEntries(entriesEntities);
//        book(vouchersEntity);
//        Long voucherNumber = vouchersEntity.getVoucherNumber();
//        System.out.println("voucherNumber:" + voucherNumber);
//        FiVoucherReturnTo fiVoucherReturnTo = new FiVoucherReturnTo(voucherNumber, fiVoucherReturnItemList);
//        System.out.println("fiVoucherReturnTo：" + fiVoucherReturnTo);
        return null;
    }

}