package edu.iu.selzheng.primesservice.controller;

import edu.iu.selzheng.primesservice.rabbitmq.MQSender;
import edu.iu.selzheng.primesservice.service.IPrimesService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/primes")
public class PrimesController {
    IPrimesService primesService;
    private final MQSender mqSender;
    public PrimesController(IPrimesService primesService, MQSender mqSender) {
        this.primesService = primesService;
        this.mqSender = mqSender;
    }
    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable int n) {
        boolean result = primesService.isPrime(n);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((Jwt) principal).getSubject();
        mqSender.sendMessage(username, n, result);
        return result;
    }

}
