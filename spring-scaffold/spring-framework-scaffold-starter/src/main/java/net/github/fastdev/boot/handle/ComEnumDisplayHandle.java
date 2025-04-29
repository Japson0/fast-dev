package net.github.fastdev.boot.handle;

import net.github.fastdev.common.model.ComEnum;

/**
 * <P><B>枚举显示处理器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface ComEnumDisplayHandle {


    String getDisplay(ComEnum comEnum);
}
