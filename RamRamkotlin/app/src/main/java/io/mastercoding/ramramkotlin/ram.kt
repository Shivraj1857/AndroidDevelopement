package io.mastercoding.ramramkotlin

import java.util.Scanner

fun main(){
//    val fruits=listOf<String>("Apple","Banana","Chikoo")
//    for (item in fruits)
//    {
//        println(item)
//    }

//    val vegatable=mutableListOf("Methi","kothimbir","palak")
//    for (item in vegatable)
//    {
//        println(item)
//    }
//
//    vegatable.add("kanda")
//    for (item in vegatable)
//    {
//        println(item)
//    }
//    vegatable[3]="alu"
//
//    val color=arrayListOf("Red","Yellow","Orange","Pink")
//    color.add("black")
//    color.removeAt(0)
//
//    for(item in color)
//    {
//        println(item )
//    }

    //Immutable Map
//    val fruits=mutableMapOf("Mango" to 10,"Banana" to 12,"Apple" to 5)
//    val Count_of_Apple=fruits["Mango"]
//    for ((key,value ) in fruits)
//    {
//        println("$key : $value")
//    }


    //Lambda function
//    val printfun={println("ram ram bhau")}
//    printfun()
//    printfun.invoke()


    //basic lamda expression
    //with parameter and return type
//    val add:(Int,Int)->Int={a,b ->a+b}
//    val result=add(4,2)
//    println(result)
//
//    //with parameter and no return value
//    val add1:(Int,Int)-> Unit={a,b->println(a+b)}
//    add1(2,3)
//
//    //without parameter and return type
//    val add2:()->String={"Shiva laksha de re baba"}
//    println(add2())

    //without para and no return val
//    val add3:()-> Unit={println("asach kaar abhyas")}
//    add3()

    //Direct lamda expression
//    println({a:Int,b:Int->a*b}(4,5))

//    val number=listOf(1,2,3,4)
//    val anafunction=fun(x:Int):Int{return x*x }
//    val sqnumber=number.map(anafunction)
//    println(sqnumber)

    //4 types of annominyes function
    //parameter with return value
//    val multply=fun(a:Int,b:Int):Int{return a*b }
//
//   println( multply(5,2))
//    //parameter with no return value
//    val multiply1=fun(a:Int,b:Int):Unit{println(a*b ) }
//    multiply1(10,20)
//
//
//    //no parameter with return value
//    val multiply2=fun(a:Int,b:Int){println(a*b)}
//    multiply2(2,3)
//
//    //no parameter with no retrn value
//    val multiply3=fun():Unit{ return println("hello baccho")}
//    multiply3()

//    val numbers=listOf(1,2,3,4,5)
//    val square=numbers.map{it*it}
//    println(square)

    var s1= Scanner(System.`in`)
//    print("Enter the the radius: ")
//    var r=s1.nextInt()
//    var circleA=3.14*r*r;
//    println("Area of Circle is"+circleA)
//    var circleP=2*3.14*r;
//    println("Perimeter of a circle is "+circleP)

    //swap a value
//    var first="a"
//    var second="b"
//    var temp=first
//    first=second
//    second=temp
//
//    println(first)
//    println(second)
//
//    val s1="XYZ"
//    s1.reversed()
//    println(s1)
//println("enter")
//    val str=s1.nextLine()
//
//    var revesed=str.reversed()
//    println(revesed)
//    for (i in str.size-1 downTo 0)
//    {
//        revesed=revesed+str[i]
//    }
//    println(revesed)

    println("Enter the num1")
    val num1=s1.nextInt()
    println("Enter the num2")
    val num2=s1.nextInt()
    println("Enter the num3")
    val num3=s1.nextInt()

    if((num1>num2)&&(num1>num3))
    {
        println("num1 is greater")

    }
    else{
        println("num3 is greater")
    }

    if((num2>num3)&&(num2>num1))
    {
        println("num2 is greater")
    }
    else{
        println("num3 is greater")
    }












}