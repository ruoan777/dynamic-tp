package org.dromara.dynamictp.example.controller;

import org.dromara.dynamictp.core.DtpRegistry;
import org.dromara.dynamictp.core.support.task.runnable.NamedRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Redick01
 */
@Slf4j
@RestController
@SuppressWarnings("all")
public class TestController {

    @Resource
    private ThreadPoolExecutor dtpExecutor1;

    @GetMapping("/dtp-zookeeper-example/test")
    public String test() throws InterruptedException {
        task();
        return "success";
    }

    public void task() throws InterruptedException {
        Executor dtpExecutor2 = DtpRegistry.getExecutor("dtpExecutor2");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            dtpExecutor1.execute(() -> {
                log.info("i am dynamic-tp-test-1 task");
            });
            dtpExecutor2.execute(NamedRunnable.of(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("i am dynamic-tp-test-2 task");
            }, "task-" + i));
        }
    }
}
