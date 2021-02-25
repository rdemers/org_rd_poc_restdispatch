package org.demers.pdc.restplus;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

@JsonComponent
public class MessageMapper
{
    public static class MessageJsonSerializer extends JsonSerializer<Message>
    {
        @Override
        public void serialize(Message message, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException, JsonProcessingException
        {
            //String test = message.getContent();

            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", message.getId());
            jsonGenerator.writeStringField("type", message.getType());
            jsonGenerator.writeRaw(",\"content\": " + message.getRawContent()); // Soyons conscient que nous utlisons
            jsonGenerator.writeEndObject();                                     // la version RAW.
        }
    }
 
    public static class MessageJsonDeserializer extends JsonDeserializer<Message>
    {
        @Override
        public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
          throws IOException, JsonProcessingException
        {
            TreeNode treeNode      = jsonParser.getCodec().readTree(jsonParser);
            IntNode idNode       = (IntNode) treeNode.get("id");
            TextNode typeNode    = (TextNode) treeNode.get("type");

            // Le contenu dynamique.
            // Ici ... C'est une preuve de concept. 
            //         Alors la logique d'affaires sert principalement pour une démmonstration.
            //         Rafinement serait requis. 
            TreeNode contentNode =  treeNode.get("content");
            String content = contentNode.toString();

            return new Message(idNode.longValue(),typeNode.textValue(),content);
        }
    }
}