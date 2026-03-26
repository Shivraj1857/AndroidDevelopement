package io.mastercoding.myandroidportfolio.repository

import io.mastercoding.myandroidportfolio.R
import io.mastercoding.myandroidportfolio.model.AppModel


class AppRepository {

    fun getAppList(): List<AppModel> {
        return listOf(

            AppModel(
                appName = "Calculator App",
                appDescription = "Basic calculator using java",
                appIcon =R.drawable.calculator1,
                packageName ="io.mastercoding.easycalculator"
            ),

            AppModel(
                appName = "Currency Convertor",
                appDescription = "It convert the $ currency into Indian Currency",
                appIcon = R.drawable.money2,
                packageName ="io.mastercoding.currencyconvertorapp"
            ),

            AppModel(
                appName = "Lottery App",
                appDescription = "Random ticket generation",
                appIcon = R.drawable.raffle3,
                packageName ="io.mastercoding.thelotteryapp"
            ),

            AppModel(
                appName = "Planet App",
                appDescription = "In Displaying All planet with some Description",
                appIcon =R.drawable.planet4,
                packageName ="io.mastercoding.planetsapp"
            ),

            AppModel(
                appName = "Volume Calculator",
                appDescription = "In this app we calculate Volume of some entity",
                appIcon =R.drawable.volume5,
                packageName ="io.mastercoding.volumecalculator"
            ),

            AppModel(
                appName = "Grocery App",
                appDescription = "Grocery",
                appIcon =R.drawable.grocery6,
                packageName ="com.mastercoding.groceryapp"
            ),

            AppModel(
                appName = "Sports App",
                appDescription = "app",
                appIcon =R.drawable.sports7,
                packageName ="com.mastercoding.sportsapp"
            ),

            AppModel(
                appName = "BroadCast Receiver small practical",
                appDescription = "app",
                appIcon =R.drawable.promote8,
                packageName ="io.mastercoding.broadcast_receiver_intent_filter"
            ),

            AppModel(
                appName = "Services",
                appDescription = "app",
                appIcon =R.drawable.service9,
                packageName = "io.mastercoding.serviceapp"
            ),

            AppModel(
                appName = "Navigation Drawer",
                appDescription = "drawer and view pager concept",
                appIcon =R.drawable.drawer10,
                packageName ="io.mastercoding.navdrawerapplication"
            ),

            AppModel(
                appName = "Navigation App",
                appDescription = "app",
                appIcon =R.drawable.img10,
                packageName ="io.mastercoding.navigationapp"
            ),

            AppModel(
                appName = "Quadratic Equation Solver",
                appDescription ="app",
                appIcon =R.drawable.qe12,
                packageName ="io.mastercoding.quadraticequationsolver"
            ),

            AppModel(
                appName = "ViewModel",
                appDescription = "app",
                appIcon =R.drawable.viewmodel13,
                packageName ="io.mastercoding.viewmodelapp"
            ),

            AppModel(
                appName = "Contact Manager App",
                appDescription = "app",
                appIcon =R.drawable.img14,
                packageName ="io.mastercoding.contactmanagerapp"
            ),

            AppModel(
                appName = "note Taking App",
                appDescription = "app ",
                appIcon =R.drawable.img15,
                packageName ="io.mastercoding.notetakingapp"
            ),

            AppModel(
                appName = "Firebase",
                appDescription = "simple operation we do on firebase",
                appIcon =R.drawable.firebase16,
                packageName = "io.mastercoding.firebaseapp"
            ),

            AppModel(
                appName = "Top Company App",
                appDescription = "retro",
                appIcon =R.drawable.company17,
                packageName ="io.mastercoding.restrofitapp"
            ),

            AppModel(
                appName = "eCommerce App ",
                appDescription ="app ",
                appIcon =R.drawable.ecommerce18,
                packageName ="io.mastercoding.ecommerceapp"
            ),

            AppModel(
                appName = "quote",
                appDescription = "app",
                appIcon =R.drawable.quote19,
                packageName ="io.mastercoding.quotecomposeapp"
            ),
        )
    }
}
