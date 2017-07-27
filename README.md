RatingBar
=====
一个自定义的Android评分控件!
<br>可更改图标，大小，间距，可选半图模式，点击模式，拖动模式，另外有回调监听. </br>

效果图
-------
初始化：
<br>![](screenshot/img1.jpg "初始化")</br>

选择评分：
<br>![](screenshot/img2.jpg "评分")</br>

使用方法
-------
在module级 build.gradle下

    dependencies {
        compile 'com.xl.ratingbar:ratingbar:0.1.1'
    }
    
在XML中（属性也可在代码中实现）

    <com.xl.ratingbar.MyRatingBar
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:touchable="true"
        app:clickable="false"
        app:starCount="5"
        app:starNum="2"
        app:halfstart="true"
        app:starEmpty="@mipmap/fat_star_empty"
        app:starHalf="@mipmap/fat_star_half"
        app:starFill="@mipmap/fat_star_full"
        app:starImageHeight="30dp"
        app:starImageWidth="30dp"
        app:starImagePadding="15dp"
        >
    </com.xl.ratingbar.MyRatingBar>
    
<div>
    <table border="0">
	  <tr>
	    <th>name</th>
	    <th>format</th>
       <th>description</th>
	  </tr>
	  <tr>
	    <td>starCount</td>
	    <td>dimension</td>
      <td>设置总个数</td>
	  </tr>
    <tr>
	    <td>starNum</td>
	    <td>dimension</td>
      <td>默认选择个数</td>
	  </tr>
    <tr>
	    <td>clickable</td>
	    <td>boolean</td>
      <td>是否可点击</td>
	  </tr>
    <tr>
	    <td>touchable</td>
	    <td>boolean</td>
      <td>是否可拖动</td>
	  </tr>
    <tr>
	    <td>halfstart</td>
	    <td>boolean</td>
      <td>是否支持选择0.5</td>
	  </tr>
    <tr>
	    <td>starEmpty</td>
	    <td>drawable</td>
      <td>未选择图标</td>
	  </tr>
    <tr>
	    <td>starHalf</td>
	    <td>drawable</td>
      <td>选择0.5图标</td>
	  </tr>
    <tr>
	    <td>starFill</td>
	    <td>drawable</td>
      <td>选择图标</td>
	  </tr>
   <tr>
	    <td>starImageHeight</td>
	    <td>dimension</td>
      <td>图标的高度</td>
	  </tr>
    <tr>
	    <td>starImageWidth</td>
	    <td>dimension</td>
      <td>图标的宽度</td>
	  </tr>
    <tr>
	    <td>starImagePadding</td>
	    <td>dimension</td>
      <td>图标之间Padding</td>
	  </tr>
    </table>
</div>

注意：
-------
touchable、clickable同时为true,默认为拖动模式！
starHalf 并不是半个图，如：<br>![](screenshot/img3.jpg "半颗星")</br>

详细可见：
[我的简书](http://www.jianshu.com/p/c108e8b08e59 "一只懵懂无知的博客")
