package org.demers.pdc.restplus;

public class Message
{
    private Long id;
    private String type;
    private String content; // C'est n'importe quoi calisse.

    public Message()
    {
    	super();
        this.id = null;
        this.type = null;
        this.content = null;
    }
    
    public Message(long id, String type, String content)
    {
        this.id = id;
        this.type = type;
        this.content = content;
    }

	public Message(Message message)
	{
        this.id = message.id;
        this.type = message.type;
        this.content = message.content;
	}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getContent()
    {
        // Éliminer les " ... S'il y a lieu
        if ((content == null) || (content.length() < 2))
            return content;

        // Faire le ménage.
        StringBuffer mb = new StringBuffer(content);
        if (mb.charAt(0) == '"') mb.deleteCharAt(0);
        if (mb.charAt(mb.length()-1) == '"') mb.deleteCharAt(mb.length()-1);

        return mb.toString();
    }

    public String getRawContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}