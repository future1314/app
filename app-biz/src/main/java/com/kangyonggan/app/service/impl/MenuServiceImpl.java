package com.kangyonggan.app.service.impl;

import com.kangyonggan.app.mapper.MenuMapper;
import com.kangyonggan.app.model.Menu;
import com.kangyonggan.app.service.MenuService;
import com.kangyonggan.common.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kangyonggan
 * @since 12/6/18
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public boolean hasMenu(Long userId, String[] menuCodes) {
        return menuMapper.selectExistsUserMenuCodes(userId, menuCodes);
    }

    @Override
    public List<Menu> findMenusByUserId(Long userId) {
        List<Menu> menus = menuMapper.selectMenusByUserId(userId);
        List<Menu> wrapList = new ArrayList<>();
        return recursionLeafList(menus, wrapList, StringUtils.EMPTY);
    }

    @Override
    public List<Menu> findParentMenusByCode(String menuCode) {
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuCode(menuCode);
        menu = myMapper.selectOne(menu);
        menus.add(menu);

        while (StringUtils.isNotEmpty(menu.getParentCode())) {
            menuCode = menu.getParentCode();
            menu = new Menu();
            menu.setMenuCode(menuCode);
            menu = myMapper.selectOne(menu);
            menus.add(menu);
        }

        Collections.reverse(menus);
        return menus;
    }

    /**
     * 递归构造叶子节点
     *
     * @param from
     * @param toList
     * @param parentCode
     * @return
     */
    private List<Menu> recursionLeafList(List<Menu> from, List<Menu> toList, String parentCode) {
        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (parentCode.equals(menu.getParentCode())) {
                List<Menu> leaf = new ArrayList<>();
                menu.setLeaf(leaf);
                toList.add(menu);
                recursionLeafList(from, leaf, menu.getMenuCode());
            }
        }
        return toList;
    }
}
