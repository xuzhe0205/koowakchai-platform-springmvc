package com.koowakchai.common.email;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailUtil;
import com.koowakchai.common.reciept.StorePDFService;
import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AnnexEMailService {

    @Autowired
    private StorePDFService storePDFService;

    public boolean sendEmail(int businessCategory, List<Object> orders){
        String emailContent = "";
        String emailHeader = "";
        String recieptPath = "";
        try{
            switch (businessCategory){
                case 1:
                    TTotalOrderEntity tTotalOrderEntity = new TTotalOrderEntity();
                    List<TTotalOrderEntity> tTotalOrderEntityList = new ArrayList<>();
                    for (Object order : orders){
                        tTotalOrderEntity = (TTotalOrderEntity) order;
                        tTotalOrderEntityList.add(tTotalOrderEntity);
                        emailHeader = "<h2>亲爱的 " + tTotalOrderEntity.getCustomerName() + "/Hello " + tTotalOrderEntity.getCustomerName() + ":<br><br>感谢您使用Koowakchai，您的订单已经确认!<br>Thanks for using Koowakchai, your order has been placed!</h2><br><hr><br><br>";
                        emailContent += "<p>Your order with confirmation code #" + tTotalOrderEntity.getId() + " was placed on " + tTotalOrderEntity.getPaidDatetime() + "; </p><br>";
                    }
                    emailContent += "<p>Check for more details in the receipt from the attachment >>></p>";
                    recieptPath = storePDFService.createPdf(tTotalOrderEntityList, null);
                    System.out.println("dfasfasfasfdas" + tTotalOrderEntity.getCustomerEmail());
                    MailUtil.send(tTotalOrderEntity.getCustomerEmail(), "测试", emailHeader+emailContent, true, FileUtil.file(recieptPath));
                    break;
            }
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
}
