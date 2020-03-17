package com.rayhahah.easyworld.architecture.base;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;

/**
 * ┌───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│ │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│ ┌┐    ┌┐    ┌┐
 * └───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘ └┘    └┘    └┘
 * ┌──┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐┌───┬───┬───┐┌───┬───┬───┬───┐
 * │~`│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp ││Ins│Hom│PUp││N L│ / │ * │ - │
 * ├──┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤├───┼───┼───┤├───┼───┼───┼───┤
 * │Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ ││Del│End│PDn││ 7 │ 8 │ 9 │   │
 * ├────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤└───┴───┴───┘├───┼───┼───┤ + │
 * │Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │             │ 4 │ 5 │ 6 │   │
 * ├─────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤    ┌───┐    ├───┼───┼───┼───┤
 * │Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │    │ ↑ │    │ 1 │ 2 │ 3 │   │
 * ├────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤┌───┼───┼───┐├───┴───┼───┤ E││
 * │Ctrl│Ray │Alt │         Space         │ Alt│code│fuck│Ctrl││ ← │ ↓ │ → ││   0   │ . │←─┘│
 * └────┴────┴────┴───────────────────────┴────┴────┴────┴────┘└───┴───┴───┘└───────┴───┴───┘
 *
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2020/3/8
 * @tips 这个类是Object的子类
 * @fuction
 */
public class NavGraphBuilder {
    public static void build(NavController controller, FragmentActivity activity, int containerId) {
        NavigatorProvider navigatorProvider = controller.getNavigatorProvider();
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(navigatorProvider));

//        FragmentNavigator fragmentNavigator = navigatorProvider.getNavigator(FragmentNavigator.class);
        KeepStateFragmentNavigator fragmentNavigator = new KeepStateFragmentNavigator(activity, activity.getSupportFragmentManager(), containerId);
        navigatorProvider.addNavigator(fragmentNavigator);
        ActivityNavigator activityNavigator = navigatorProvider.getNavigator(ActivityNavigator.class);

        buildDestination(navGraph, fragmentNavigator, activityNavigator);
        controller.setGraph(navGraph);
    }

    private static void buildDestination(NavGraph navGraph, KeepStateFragmentNavigator fragmentNavigator, ActivityNavigator activityNavigator) {
//        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
//        for (Destination value : destConfig.values()) {
//            if (value.isFragment) {
//                FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
//                destination.setClassName(value.className);
//                destination.setId(value.id);
//                destination.addDeepLink(value.pageUrl);
//
//                navGraph.addDestination(destination);
//            } else {
//                ActivityNavigator.Destination destination = activityNavigator.createDestination();
//                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), value.className));
//                destination.setId(value.id);
//                destination.addDeepLink(value.pageUrl);
//                navGraph.addDestination(destination);
//            }
//
//            if (value.asStarter) {
//                /**
//                 * 作为启动的首个节点
//                 */
//                navGraph.setStartDestination(value.id);
//            }
//        }
    }

}
