package com.myProducts.controller;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

	
	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleMessage(SOAPMessageContext msgCtx) {
		final Boolean outInd = (Boolean) msgCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // Handler must only add security headers to outbound messages
        if (outInd.booleanValue()) {
            try {
                // Get the SOAP Envelope
                final SOAPEnvelope envelope = msgCtx.getMessage().getSOAPPart().getEnvelope();

                // Header may or may not exist yet
                SOAPHeader header = envelope.getHeader();
                if (header == null)
                    header = envelope.addHeader();

                // Add WSS Usertoken Element Tree 
                final SOAPElement security = header.addChildElement("Security", "wsse",
                        "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                final SOAPElement userToken = security.addChildElement("UsernameToken", "wsse");
                userToken.addChildElement("Username", "wsse").addTextNode("admin");
                SOAPElement se =  userToken.addChildElement("Password", "wsse");
                		se.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                		se.addTextNode("admin");
                
            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
