package email

import java.text.MessageFormat

/**
 *  
 * User: Mr.ZUO
 * Date: 13-1-27 上午11:15
 */
class SendMail {

    private SendMail(){}

    /**
     * 邮件发送
     * @param userName
     *      用户名
     * @param uid
     *      登录名
     * @param res
     *      申请是否成功(true成功，false失败)
     * @param path
     *      邮件内容模板文件地址
     *
     */
    public void send(String userName,String uid,String path,String email,int res){
        //动态读取邮件配置文件
        Properties prop = new Properties();
        InputStream stream = SendMail.class.getResourceAsStream("/mail.properties");
        prop.load(stream);

        //这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost(prop.getProperty("mailServerHost").trim());
        mailInfo.setMailServerPort(prop.getProperty("mailServerPort").trim());
        mailInfo.setValidate(true);
        mailInfo.setUserName(prop.getProperty("userName").trim());
        mailInfo.setPassword(prop.getProperty("password").trim());//您的邮箱密码
        mailInfo.setFromAddress(prop.getProperty("userName").trim());
        mailInfo.setToAddress(email);
        if(res==0){
            mailInfo.setSubject("申请审批成功");
            mailInfo.setContent(mailContent(userName,uid,path));
        }else if(res==1){
            mailInfo.setSubject("申请中");
            mailInfo.setContent(mailContent(userName,uid,path));
        }else if(res==2){
            mailInfo.setSubject("申请被退回");
            mailInfo.setContent(mailContent(userName,uid,path));
        }
        //这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendHtmlMail(mailInfo);//发送HTML格式

    }

    /**
     * 设置邮件内容
     * @param userName
     * @param uid
     * @return
     */
    public String mailContent(String userName,String uid,String path){
        String content = "";
        try {
            String encoding = "UTF-8"; // 字符编码(可解决中文乱码问题 )
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT = null;
                while ((lineTXT = bufferedReader.readLine()) != null) {
                    MessageFormat form = new MessageFormat(lineTXT.toString().trim());
                    String str
                    Object[] testArgs = [userName,uid];
                    content +=form.format(testArgs);
                }
                println(content)
                read.close();
            } else {
                System.out.println("找不到指定的文件！");
            }
            return content;
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
    }


}
