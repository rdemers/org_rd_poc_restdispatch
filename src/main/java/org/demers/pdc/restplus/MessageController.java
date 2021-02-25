package org.demers.pdc.restplus;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController
{
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Message getMessage(@RequestParam(value="type", defaultValue="text") String type,
                              @RequestParam(value="content", defaultValue="content") String content)
    {
        return new Message(counter.incrementAndGet(),type,content);
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Message greeting(@RequestBody Message message)
    {
        return new Message(message);
    }
}
