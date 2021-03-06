package mytest

/**
  * Created by fqc on 2016/7/24.
  */
object Higher_Order_Func extends App {
  val arr = Array(1 to 10: _*)
  println("arr = " + arr.toBuffer) //toBuffer buffer重写了数组的tostring，较为便捷
  //需求1.每个元素都乘以10

  //  原始做法
  for (elem <- arr) {
    println(elem*10)
  }
  //需求2.过滤每个偶数函数
  for (elem <- arr) {
    if (elem % 2 == 0) {
      println(elem*10)
    }
  }
  //变型
  for (elem <- arr if elem%2==0) {
    println(elem*10)
  }
////////////////////////////////////////////////////////////////////////////////

  //使用高阶函数 需求，数组变换
  //方式1 定义业务函数作为参数传入
  val arr2 = Array("a","b","c","d")

  def changeArrayElem(arr:Array[String],func:(String)=>String):Array[String]={
   /* for (elem <- arr) {//只适合读取
      func(elem)
    }*/
    for (i <- 0 until arr.length) {
          arr(i) = func(arr(i))
    }
    arr
  }
  //将字符变为大写
  def func_toUpper(s:String):String = {
    s.toUpperCase()//有返回值 ，因此直接返回喽
  }
  //调用
  val result: Array[String] = changeArrayElem(arr2,func_toUpper)
  println("result = " + result.toBuffer)

  //方式2 匿名函数作为参数传入
  val result2: Array[String] = changeArrayElem(arr2,(s:String)=>{s.toUpperCase()})
  println("result2 = " + result2.toBuffer)
  //方式3 匿名函数传给值函数作，然后作为参数传入
  val func_toUpperCase = (s:String)=>s.toUpperCase()
  val result3: Array[String] = changeArrayElem(arr2,func_toUpperCase)
  println("result3 = " + result3.toBuffer)

  //以后匿名函数方式使用的很多
  //现在做更多的变形
  //将字符加后缀
  val result4 = changeArrayElem(arr2,(s:String)=>s+"_$$")
  println("result4 = " + result4.toBuffer)

  //将字符double
  val result5 = changeArrayElem(arr2,(s:String)=>s*2+"--")
  println("result5 = " + result5.toBuffer)


  //高阶函数的简化   上面的类型是可以省略的，且有些变量使用_替换更加简便
  val result6: Array[String] = changeArrayElem(arr2,(s:String)=>s*2)
  val result7: Array[String] = changeArrayElem(arr2,(s)=>s*2)
  val result8: Array[String] = changeArrayElem(arr2,s=>s*2)
  val result9: Array[String] = changeArrayElem(arr2,_*2)

  println("result6 = " + result6.toBuffer)
  println("result7 = " + result7.toBuffer)
  println("result8 = " + result8.toBuffer)
  println("result9 = " + result9.toBuffer)
}
