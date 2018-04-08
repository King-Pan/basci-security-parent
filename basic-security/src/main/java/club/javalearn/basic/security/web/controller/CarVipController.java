package club.javalearn.basic.security.web.controller;

import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.domain.CarVip;
import club.javalearn.basic.security.service.CarVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/car/")
public class CarVipController {

    @Autowired
    private CarVipService vipService;

    //@RequiresPermissions("vips:view")
    @GetMapping(value = {"vip","vip/"})
    public ModelAndView vipPage() {
        return new ModelAndView("car/vip");
    }


    @GetMapping("/vips")
    //@RequiresPermissions("vips:list")
    public Message<CarVip> userList(CarVip vip, @PageableDefault Pageable pageable) {
        return vipService.getList(vip, pageable);
    }

}
