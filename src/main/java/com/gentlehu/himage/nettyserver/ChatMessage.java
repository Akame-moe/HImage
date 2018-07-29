package com.gentlehu.himage.nettyserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gentle-hu on 2018/7/25 5:37.
 * Email:me@gentlehu.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {


    private String username;
    private String message;

    public static ChatMessage make(String username,String message) {
        return new ChatMessage(username, message);

    }
}