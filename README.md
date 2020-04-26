# Flight
一款android路由库

## 依赖
当前版本 1.0.0

## 安装
根目录的build.gradle
```
allprojects {
    repositories {
        ......        
        maven { url 'https://jitpack.io' }
    }
}

```
依赖
```
implementation 'com.github.CrazyWalk:Flight:{latestVersion}'
annotationProcessor 'com.github.CrazyWalk:Flight:{latestVersion}'
```

## 使用
build.gradle
```
android {
     ....
    defaultConfig {
        ....
    
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = ['flightModule': 'AppRouteModule']
            }
        }

    }
```

Application
```
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Flight.registerModule(this, "AppRouteModule");
    }
}

```

加入路由组件
```
@RouteComponent("/login")
@Controller(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
}
```

跳转
```
 Flight.with(this)
     .request(new RouteRequest.Builder()
             .path("/login")
             .build())
     .out()
```





## License
<pre>
Copyright (c) 2020, Andy Hong. All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
</pre>