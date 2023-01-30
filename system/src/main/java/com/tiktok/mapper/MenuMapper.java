package com.tiktok.mapper;

import com.tiktok.domain.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @SelectProvider(type = MenuProvider.class, method = "selectByIdSQL")
    @Results(id = "menuMap", value = {
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "subCount", column = "parent_id"),
            @Result(property = "menuName", column = "menu_name"),
            @Result(property = "iFrame", column = "i_frame"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "updateBy", column = "update_by"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    Menu findMenuById(Long menuId);

    @InsertProvider(type = MenuProvider.class, method = "insertSQL")
    int insertMenu(Menu menu);

    @UpdateProvider(type = MenuProvider.class, method = "updateSQL")
    int updateMenu(Menu menu);

    @DeleteProvider(type = MenuProvider.class, method = "deleteByIdSQL")
    int deleteMenuById(Long menuId);

    @SelectProvider(type = MenuProvider.class, method = "selectSubSQL")
    @ResultMap("menuMap")
    List<Menu> findSubmenu(Long menuId, String username);

    @SelectProvider(type = MenuProvider.class, method = "selectPeerSQL")
    @ResultMap("menuMap")
    List<Menu> findPeerMenu(Long menuId, String username);

    @SelectProvider(type = MenuProvider.class, method = "selectParentSQL")
    @ResultMap("menuMap")
    List<Menu> findParentMenu(Long menuId, String username);

    @SelectProvider(type = MenuProvider.class, method = "selectRootMenuSQL")
    @ResultMap("menuMap")
    List<Menu> findRootMenu(String username);
}
