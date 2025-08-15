
package com.anas.foodmenu.data

import com.anas.foodmenu.R
import com.anas.foodmenu.model.FoodMenu

class DataSource() {
    fun loadMenu(): List<FoodMenu> {
        return listOf<FoodMenu>(
            FoodMenu(R.string.menu1,R.string.rate1, R.drawable.menu1 , R.string.desc1),
            FoodMenu(R.string.menu2, R.string.rate2, R.drawable.menu2, R.string.desc2),
            FoodMenu(R.string.menu3, R.string.rate3, R.drawable.menu3, R.string.desc3),
            FoodMenu(R.string.menu4, R.string.rate4, R.drawable.menu4, R.string.desc4),
            FoodMenu(R.string.menu5, R.string.rate5, R.drawable.menu5, R.string.desc5),
            FoodMenu(R.string.menu6, R.string.rate6, R.drawable.menu6, R.string.desc6),
            FoodMenu(R.string.menu7, R.string.rate7, R.drawable.menu7, R.string.desc7),
            FoodMenu(R.string.menu8, R.string.rate8, R.drawable.menu8, R.string.desc8),
            FoodMenu(R.string.menu9, R.string.rate9, R.drawable.menu9, R.string.desc9),
            FoodMenu(R.string.menu10, R.string.rate10, R.drawable.menu10, R.string.desc10),
            FoodMenu(R.string.menu11, R.string.rate11, R.drawable.menu11, R.string.desc11),
            FoodMenu(R.string.menu12, R.string.rate12, R.drawable.menu12, R.string.desc12),
            FoodMenu(R.string.menu13, R.string.rate13, R.drawable.menu13, R.string.desc13),
            //FoodMenu(R.string.menu14, R.drawable.menu1),
            //FoodMenu(R.string.menu15, R.drawable.menu1)
        )
    }
}
