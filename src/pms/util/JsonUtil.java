package pms.util;

import java.io.OutputStream;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

public class JsonUtil {
	
	public static void writeMessage(String messageType, String message, OutputStream out){
		JsonGenerator gen = Json.createGenerator(out);
		gen.writeStartObject()
		.write("msgtype",messageType)
		.write("msg", message)
		.writeEnd();
		gen.close();
	}

	public static void main(String[] args) {
		writeMessage("error", "inalid", System.out);

	}

}
