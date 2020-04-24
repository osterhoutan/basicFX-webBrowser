Settings Config User Guid:
=============================
_Andrew Osterhout (a02201315)_<br/>
_USU: Sp2020 -> CS2410: Event Driven Programing and GUI's_<br/>
_Final Project: basicFX-webBrowser_

## About
This application makes use of a JSON config file located in the program's `AppData` directory (it's location is system dependent) in the file `AppData/config/settings.json` file.
Most surface level user friendly settings are editable through the application's settings menu and should be adjusted there for easiest use.

## Disclaimer and Debugging
If you are not experienced enough with JSON or other JSON based settigns systems like that which vscode uses should exercise caution when editing the settings file.

Do not edit the `settings.json` while the application is running, as the application will overwrite the file with the configuration stored in it's memory upon close, and periodically while running.
Which means that changes you made will not be remembered.
Additionally, changes made in the `settings.json` will not take effect until the next time to application is started. 

While there are no changes you can make to the `settings.json` file will cause you to loose any application specific data (data on open webpages may still be lost), it may cause the application to crash if improperly configured.
Usually though if the change you made is not proper the application will revert to the application defaults.

If you can't remember what changes you made and things aren't working, or your change isn't taking effect.
Try opening up the file in an editor that supports linting JSON files like vscode, notepad++, neovim, sublime text, etc or online tools to check that your file is properly formatted.

As a last resort, removing all of the contents of the file and replacing it with `{}`. The application wll revert to using all defaults upon next boot.


## Config Settings:
### General Settings:
settings that can and probably should be changed and updated in the settings menu of the application.

| Config setting key:       | Description: | Possible Values: | Default Value: |
|--------------------------:|:-------------|:-----------------|:--------------:|
| `"newTabAsHomePage"`      | whether new tabs should open to the newTab page or a home page specified with the `"homePage"`    |  `true`: open new browser tabs to newTab page <br/>`false`: Open open new browser tabs to URL of the Home Page    | `true`    |
| `"homePage"`              | The URL of the page you want to land on if you have `"newTabAsHomePage"` set to `false` and you open a new browser tab    | Any valid URL or URI  | `"https://www.google.com"`    |
| `"theme"`                 | the name of the "theme" to be used for the browser    | this name is the same as the file name of the `.css` sheet configured for themeing javafx elements located in the application theme resource folder or the `AppData/themes` directory for custom user themes   | `bootstrap3`  | 
| `"searchEngine"`          | Which search engine is to be used by default when you type a non url or query request into the browser's "omnibar"    | `"Google"`, `"Google Scholar"`, `"Google Images"`, `"Youtube"`, `"Bing"`, `"Duck Duck GO!"`, `"Yahoo"`, `"Amazon Store"`, `"GitHub"`, or `"Custom"`   | `"Google"`    |
| `"customSearchEngine"`    | This is a JSON object _(i.e. `key:value` pairs enclosed in `{}`'s)_ that contains values for the user definable search engine.    | See section on the [custom search engine settings](#custom-search-engine-settings) below.   | --    |

#### Custom Search Engine Settings:
These Settings need to be enclosed in a JSON object _(i.e. `key:value` pairs enclosed in `{}`'s)_ attached to the `"customSearchEngine"` Config setting key:

        "customSearchEngine": { ... config values here ...}

| Attribute key:    | Description: | Possible Values: | Default Value: |
|------------------:|:-------------|:-----------------|:--------------:|
| `"searchHeader"`  | The string you can type in the browsers "omnibar" that can be used to use the defined `"queryURL"` as the search engine to search what follows the string | Any String of greater than 3 characters, and must end with a `':'`    | `"google:"`   |
| `"queryURL"`      | The query URL for your desired online search engine   | a string representing a nearly complete URL, except with a `"%s"` where the users query encoded to URL utf-8 standards should be inserted | `"https://www.google.com/search?q=%s"`    | 
| `"type"`          | This just exists for the parser to know that this JSON object represents a `SearchEngine` object from the application, it's value acn be omitted or set to the `"searchEngine"`   | `null`, `""`, `"searchEngine"`, or the `key:value` pair omitted | `"searchEngine"`  |
| `"name"`          | This value is also ignored in the context of the Custom search engine, only exists as part of the serialization and encoding of the `SearchEngine` java object and it's JSON object form  | `null`, `""`, `"Custom"`, or the `key:value` pair omitted  | `"Custom"` |


### Advanced Settings:
Settings that can only be set in the settings.json file, exercise caution when editing/adding these.

| Config setting key:   | Description: | Possible Values: | Default Value: |
|----------------------:|:-------------|:-----------------|:--------------:|
| `"newTabPage"`        | The newTab html page, to use when a new browser tab is opened and `"newTabAsHomePage"` is set to `true`  | A string representing the file path to the desired html file, it can either be a relative file path from the applications `AppData/html/newTab` directory, an absolute file path to the file, an absolute file URI to the desired file, or a valid URL to the file (for the last two they need not be on the local machine)   | `default/tab.html`    |
| `"errorPage"`         | The html page to be used when the browser fails to load a page, to use instead of the default one  | A string representing the file path to the desired html file, it can either be a relative file path from the applications `AppData/html/newTab` directory, an absolute file path to the file, an absolute file URI to the desired file, or a valid URL to the file (all files must be on the local machine)   | `default/index.html`    |


### Misc Settings:
These settings are not really useful to change unless you need to for debugging.

| Config setting key:   | Description: | Possible Values: | Default Value: |
|----------------------:|:-------------|:-----------------|:--------------:|
| `"menuState"`         | whether or not the menu system is collapsed/closed. <br/> This value is changed every time you toggle the menu system's visibility, so changing it in this file is pretty much useless     | `true`: the menu's are collapsed <br/> `false`: the menu's are completely visible     | `false`   |
| `"restore"`           | (TBI)     |   `true` or `false`   | `false`   |