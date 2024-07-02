package com.fc.sport.controller;

import com.fc.common.utils.resp.Result;
import com.fc.sport.biz.service.BookingBizService;
import com.fc.sport.domain.service.BookingDomainService;
import com.fc.sport.vo.BookingUpdateCommand;
import com.fc.sport.vo.BookingVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 预定表 前端控制器
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/sport/booking")
public class BookingController {

    private final BookingDomainService bookingDomainService;

    private final BookingBizService bookingBizService;

    public BookingController(final BookingDomainService bookingDomainService, final BookingBizService bookingBizService) {
        this.bookingDomainService = bookingDomainService;
        this.bookingBizService = bookingBizService;
    }

    /**
     * 初始化，Web层仅做测试用
     *
     * @return
     */
    @PostMapping(value = "/init")
    public Result<Boolean> init() {
        return Result.ofSuccess(bookingDomainService.createBookingInfo());
    }

    /**
     * 更新场地信息
     * 1. 场地上锁
     * 2. 场地修改价格
     *
     * @param command 更新命令
     * @return
     */
    @PostMapping(value = "/edit")
    public Result<Boolean> edit(@RequestBody BookingUpdateCommand command) {
        return Result.ofSuccess(bookingBizService.update(command));
    }

    /**
     * 删除场馆下的所有场地信息
     *
     * @param id 场馆ID
     * @return
     */
    @DeleteMapping(value = "/deleteByPlaceId")
    public Result<Boolean> deleteByPlaceId(@RequestParam(name = "id") Long id) {
        return Result.ofSuccess(bookingBizService.deleteByPlaceId(id));
    }

    /**
     * 查询场馆下的所有场地信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryByPlaceId")
    public Result<List<BookingVO>> queryByPlaceId(@RequestParam(name = "id") Long id) {
        return Result.ofSuccess(bookingBizService.queryByPlaceId(id));
    }

}
