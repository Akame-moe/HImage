package com.gentlehu.himage.common;

import com.gentlehu.himage.utils.GsonUtil;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

/**
 * Created by gentle-hu on 2018/8/2 19:40.
 * Email:me@gentlehu.com
 */
public class JsonResultHttpMessageConverter extends GsonHttpMessageConverter {
    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), DEFAULT_CHARSET);
        writer.write(GsonUtil.toJson(o));
        writer.close();

    }
}
