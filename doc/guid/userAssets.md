User Assets Guid:
===================
_Andrew Osterhout (a02201315)_<br/>
_USU: Sp2020 -> CS2410: Event Driven Programing and GUI's_<br/>
_Final Project: basicFX-webBrowser_

## About
This application allows users to use their own custom assets, for themeing the GUI elements, for loading a custom HTML page (local, over the network, or over the internet) as the newTab page, and finally using a local HTML page as the error page (the page that is loaded when a webpage fails to load).


## The `AppData` Directory:
All user assets should be placed in their respective sub directories in the `AppData` directory.  The `AppData` directory is always named "AppData" and it's lobation on your machine is OS and setup dependent.
If this application is not actually installed but running in a dev environment _as originally intended_, the `AppData` directory is located in the root of the workspace/project directory _(i.e. `./AppData/`)_.


## Custom Themes
These need to be single file `.css` files formatted for JavaFX CSS.
It needs to be placed in the `AppData/themes/` directory.
You can choose your theme either from the settings menu in the application,
 or from the `settings.json` file using the `"theme"` key to a string that is the file name of your custom `.css` file omitting the file extension _(i.e. if the CSS file is called `myTheme.css` the config value should be `"myTheme"`)_.
You can find out more on setting config settings in the `settings.json` [here](settingsConfig.md).

Finally, in order for a new theme to take effect you need to have the application go through the startup process _(i.e. if the application is currently running when the config value for the theme is changed you must close the application and reopen it to have the new theme take effect)_


## Custom New Tab WebPage
These can be any web viewable file such as a HTML file.  The file should be placed inside of it's own directory inside of the `AppData/html/newTab/` directory.
However, using the `settings.json` config file you can specify a web page stored anywhere on your machine, on a machine accessible on the network, over the internet, or any webpage (if it is just a web apge it would be better to use set the page as your home page instead of using this customization setting).
You can choose the new newTab page using the `"newTabPage"` key value in the `settings.json` file (which you can learn how to use [here](settingsConfig.md)).
Unlike the theme this can **not** be configured in the application's settings menu, it can only be set in the aforementioned `settings.json` file.
How you specify which newTab page to use follow the instructions listed [here](settingsConfig.md#advanced-settings).


## Custom Error Page:
These can be any web viewable file such as a HTML file.  The file must be placed inside of it's own directory inside of the `AppData/html/error/` directory.
You can choose the new newTab page using the `"errorPage"` key value in the `settings.json` file (which you can learn how to use [here](settingsConfig.md)).
Unlike the theme this can **not** be configured in the application's settings menu, it can only be set in the aforementioned `settings.json` file.
How you specify which newTab page to use follow the instructions listed [here](settingsConfig.md#advanced-settings).
