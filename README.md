# RePlugin-Host-Demo

一個 RePlugin Host 的專案示範

- Host Demo(宿主): [RePlugin-Host-Demo](https://github.com/TimChenDev/RePlugin-Host-Demo)
- PluginOne Demo(插件): [RePlugin-PluginOne-Demo](https://github.com/TimChenDev/RePlugin-PluginOne-Demo)
- PluginTwo Demo(插件): [RePlugin-PluginTwo-Demo](https://github.com/TimChenDev/RePlugin-PluginTwo-Demo)

## v1.0.0

- 展示了 RePlugin 的宿主與插件的實作
- 參考修改內容可以了解 RePlugin 最基礎的實現需要哪些設定
- 將三個專案切換至 tag v1.0.0, 打包插件放到 Host/assets/plugins

## v1.1.1

- 在 Host 設定 Intent 拉起 PluginOne 的 Service, 並返回 binder, 讓 Host 可以指定 PluginOne 去做什麼事
- 這個版本的功能僅牽涉到 Host & PluginOne, 請將這兩個專案切換至 tag v1.1.1, 打包插件放到 Host/assets/plugins

## v1.2.0

- 展示 PlunginTwo 使用 fetchBinder 拉起 PluginTwo, 並且取得 binder ITwo
- 可從 One 拉起 Two, Two 完成工作再回呼 One
- 將三個專案切換至 tag v1.2.0, 打包插件放到 Host/assets/plugins
