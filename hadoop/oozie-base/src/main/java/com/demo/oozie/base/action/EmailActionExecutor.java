package com.demo.oozie.base.action;

import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.oozie.ErrorCode;
import org.apache.oozie.action.ActionExecutor;
import org.apache.oozie.action.ActionExecutorException;
import org.apache.oozie.action.ActionExecutorException.ErrorType;
import org.apache.oozie.client.WorkflowAction;
import org.apache.oozie.util.XmlUtils;
import org.jdom.Element;
import org.jdom.Namespace;

public class EmailActionExecutor extends ActionExecutor{

	private final static String TYPE = ":EMAIL:";
	
	private static final String SUCCEEDED = "OK";
	private static final String FAILED = "FAIL";
	private static final String KILLED = "KILLED";
	
	private static final String DEFAULMAILSERVER = "imailchi.navtech.com";
    private static final String EMAILSERVER = "emailServer";
    private static final String SUBJECT = "emailSubject";
    private static final String MESSAGE = "emailBody";
    private static final String FROM = "emailFrom";
    private static final String TO = "emailTo";
	
	public EmailActionExecutor() {
		super(TYPE);
	}

	@Override
	public void start(Context context, WorkflowAction action)
			throws ActionExecutorException {
		try {
			Element actionXml = XmlUtils.parseXml(action.getConf());
			Namespace ns = Namespace.getNamespace("uri:custom:email-action:0.1");
			
			String server = actionXml.getChildTextTrim(EMAILSERVER, ns);
            String subject = actionXml.getChildTextTrim(SUBJECT, ns);
            String message = actionXml.getChildTextTrim(MESSAGE, ns);
            String from = actionXml.getChildTextTrim(FROM, ns);
            String to = actionXml.getChildTextTrim(TO, ns);
            
            // Check if all parameters are there
            if(server == null)
                     server = DEFAULMAILSERVER;
            if((message == null) || (from == null) || (to == null))
                     throw new ActionExecutorException(ErrorType.FAILED, ErrorCode.E0000.toString(), "Not all parameters are defined");
            // Execute action synchronously
            SendMail(server, subject, message, from, to);
            context.setExecutionData(SUCCEEDED, null);
		} catch (Exception e) {
			context.setExecutionData(FAILED, null);
            throw new ActionExecutorException(ErrorType.FAILED, ErrorCode.E0000.toString(), e.getMessage());
		}
		
	}

	@Override
	public void end(Context context, WorkflowAction action)
			throws ActionExecutorException {
		String externalStatus = action.getExternalStatus();
		WorkflowAction.Status status = externalStatus.equals(SUCCEEDED) ? WorkflowAction.Status.OK : WorkflowAction.Status.ERROR;
		context.setEndData(status, getActionSignal(status));
	}

	@Override
	public void check(Context context, WorkflowAction action)
			throws ActionExecutorException {

	}

	@Override
	public void kill(Context context, WorkflowAction action)
			throws ActionExecutorException {
		context.setExternalStatus(KILLED);
		context.setExecutionData(KILLED, null);
	}

	@Override
	public boolean isCompleted(String externalStatus) {
		return true;
	}
	
	// Sending an email
    public void SendMail(String server, String subject, String message,
                          String from, String to) throws Exception {

           // create some properties and get the default Session
           Properties props = new Properties();
           props.setProperty("mail.smtp.host", server);
           Session session = Session.getDefaultInstance(props, null);

           // create a message
           Message msg = new MimeMessage(session);

           // set the from and to address
           InternetAddress addressFrom = new InternetAddress(from);
           msg.setFrom(addressFrom);

           // To is a comma separated list
           StringTokenizer st = new StringTokenizer(to, ",");
           String [] recipients = new String[st.countTokens()];
           int rc = 0;
           while(st.hasMoreTokens())
                   recipients[rc++] = st.nextToken();
           InternetAddress[] addressTo = new InternetAddress[recipients.length];
           for (int i = 0; i < recipients.length; i++){
                   addressTo[i] = new InternetAddress(recipients[i]);
           }
           msg.setRecipients(Message.RecipientType.TO, addressTo);

           // Setting the Subject and Content Type
           msg.setSubject(subject);
           msg.setContent(message, "text/plain");
           Transport.send(msg);
    }

}
