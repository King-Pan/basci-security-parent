package club.javalearn.basic.security.common;

import lombok.Data;

import java.util.List;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-08
 **/
@Data
public class BootstrapMessage<T> implements Message<T> {
    private List<T> rows;
    private Integer start;
    private Integer limit;
    private Long total;
}
