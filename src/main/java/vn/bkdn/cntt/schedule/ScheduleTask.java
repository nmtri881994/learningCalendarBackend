package vn.bkdn.cntt.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.bkdn.cntt.Service.RegisterTimeService;
import vn.bkdn.cntt.entity.RegisterTime;
import vn.bkdn.cntt.jsonEntity.RegisterMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by XuanVinh on 5/28/2017.
 */

@Component
public class ScheduleTask {

    @Autowired
    private RegisterTimeService registerTimeService;

    @Autowired
    private SimpMessagingTemplate template;

    ObjectMapper mapper = new ObjectMapper();

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() throws JsonProcessingException {
        Date today = new Date();
        List<RegisterTime> registerTimes = registerTimeService.findAll();
        for (RegisterTime registerTime:
             registerTimes) {
            Date startTime = registerTime.getStartTime();
            Date endTime = registerTime.getEndTime();

            if(today.after(startTime) && today.before(endTime)){
                if(!registerTime.isStatus()){
                    registerTimeService.udpateRegistering(registerTime.getId(), true);

                    RegisterMessage registerMessage = new RegisterMessage(true, registerTime.getId());
                    String mess = this.mapper.writeValueAsString(registerMessage);
                    this.template.convertAndSend("/socket/register", mess);
                }
            }
            if(today.after(endTime)){
                if(registerTime.isStatus()){
                    registerTimeService.udpateRegistering(registerTime.getId(), false);

                    RegisterMessage registerMessage = new RegisterMessage(false, registerTime.getId());
                    String mess = this.mapper.writeValueAsString(registerMessage);
                    this.template.convertAndSend("/socket/register", mess);
                }

            }
        }
    }
}
