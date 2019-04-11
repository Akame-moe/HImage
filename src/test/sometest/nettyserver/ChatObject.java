package sometest.nettyserver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gentle-hu on 2018/8/24 1:42.
 * Email:me@gentlehu.com
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatObject {
    private String userName;
    private String message;
}
