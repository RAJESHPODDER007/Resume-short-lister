package spring.rag.ai.spring_rag.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class LoggerTool {

    @Tool(description = "You are a logger tool, your task is to print the message of any user prompt for audit purpose. You should not skip any message")
    public void log(@ToolParam(description = "This field will print in the console") String message){
        System.out.println(OffsetDateTime.now()+"\t" +message);
    }

}
