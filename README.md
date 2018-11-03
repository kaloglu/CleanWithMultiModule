# Android Code Style Rules

### Package structure

Package structure should be categorized by features.

```
main
    java
        login
            LoginActivity.java
            LoginFragment.java
            LoginRequest.java
        bulletin
            BaseBulletinActivity.java
            BaseBulletinFragment.java
            odds
                OddsActivity.java
                OddsRequest.java
        utils
```

### Class Member Ordering

```
imports

class name

variables
    constants
    public
    protected
    no-access modifier
    private

constructors

methods
    static methods

    lifecycle methods arranged by lifecycle order

    overriden methods

    others
        public
        protected
        private

inner classes
```

### Resource File Basics

###### File Naming

Layout file names should use the following pattern:

`${layout_name}_${feature_name}`

* Activities:

    ```
    activity_login              // CORRECT
    act_login                   // WRONG
    ```

* Fragments:

    ```
    fragment_register           // CORRECT
    frag_register               // WRONG
    ```

* Items:

    ```
    recycler_item_bulletin      // CORRECT
    item_bulletin               // WRONG
    ```

* Components:

    ```
    view_event_item             // CORRECT
    event_item                  // WRONG
    ```

* Dialogs: Dialogs names should contain `dialog_` prefix

    ```
    dialog_login.xml
    ```

* Drawables:

    ```
    ic_launcher.png        // CORRECT
    icLauncher.png         // WRONG
    iclauncher.png         // WRONG
    IcLauncher.png         // WRONG
    ic_launcher_2.png      // WRONG
    ```

    For custom drawables such as shape, layerlist, following pattern should be used: `${componentName}_${detail}`

    ```
    circle_red.xml
    circle_blue.xml
    rectangle_lightblue.xml
    ```

* Selectors

    Selector names should contain `selector_` prefix and next word should be a clear identifier.
    There is no need to add "color" or "drawable" word into the file name since they can be accessed by R.color or R.drawable.
    It will be better to find a good name to identify what exactly selector does.

    ```
    selector_blue_button.xml              // CORRECT
    selector_color_blue_button.xml        // WRONG
    color_blue_button.xml                 // WRONG
    ```

* Others: `layout prefix still can be used for general purpose such as layout.

    ```
    layout_circle.xml
    ```

###### Resource Identification

* Dimens:
Any dimensions used in the layout should be written directly in xml.
Only the common dimensions should be added to the `dimens.xml` file.

    ```
    toolbar_height
    space_default
    avatar_icon_size
    ```

* Strings:
Strings files must be separated by feature

    ```
    strings_login.xml
    strings_bulletin.xml
    ```

###### Naming

* Classes:
Class names should contain type as suffix
    ```
    LoginActivity
    LoginFragment
    EventListAdapter
    BulletinViewPagerAdapter
    EventItemViewHolder
    OddBoxView
    ```

* Fields:
    ```
    String name;          // CORRECT
    String mName;         // WRONG
    String s_name;        // WRONG
    String name_;         // WRONG
    String NAME;          // WRONG
    ```

* Constants:
Constant names use CONSTANT_CASE: all uppercase letters, with words separated by underscores, declared static final.
    ```
    static final int NUMBER = 5;
    static final String ODD_TYPE = "Mbc";
    ```

* Parameters:
One-character parameter names should be avoided.
    ```
    public void setName(String name) {...}      // CORRECT
    public void setName(String s) {...}         // WRONG
    ```

* Packages:
Package names are all lowercase, with consecutive words simply concatenated together (no underscores).
    ```
    com.kaloglu.boiler_plate.bulletinfilter            // CORRECT
    com.kaloglu.boiler_plate.bulletin_filter           // WRONG
    com.kaloglu.boiler_plate.bulletinFilter            // WRONG
    ```

* Identifier:
Camelcase pattern should be used: `@+id/${viewType}${logicalName}`

    ```
    @+id/textViewName
    @+id/buttonRegister
    ```

###### Import statements

* Wildcard imports, static or otherwise, are not used.

    ```
    import com.example.test:     // CORRECT
    import com.example.*;        // WRONG
    ```

* Import statements are not line-wrapped.

    ```
    import com.example.test;      // CORRECT
    import com.example            // WRONG
                        .test;
    ```

* In `AndroidManifest.xml` file Activity name attribute should use "." operator.

    ```
    <activity
           android:name=".ui.register.RegisterActivity"/>                 // CORRECT

    <activity
           android:name="com.kaloglu.boiler_plate.ui.register.RegisterActivity"/>     // WRONG
    ```

###### Formatting

* When defining classes, interfaces, functions that take more than one parameter, each parameter must be defined on the new line.

    ```
    listener.onItemSelected(
        context,
        instalmentViewPager.getCurrentItem(),
        numberPickerInstallment.getCurrentItem(),
        installmentOptions.getIndex()
    );
    
    listener.onItemSelected(
        new Foo(
            installmentText,
            installmentName
        ),
        installmentViewPager.getCurrentItem(),
        numberPickerInstallment.getCurrentItem(),
        installmentOptions.getIndex()
    );
    
    class MyClass(                                                                                              // CORRECT
            fooList: ArrayList<Foo>,
            private val bar: Bar,
            private val callBack: Callback
    ) : ParentClass() {

    class MyClass(fooList: ArrayList<Foo>, private val bar: Bar,                                                // WRONG
                             private val callBack: Callback) : ParentClass() {

    class MyClass(fooList: ArrayList<Foo>, private val bar: Bar, private val callBack: Callback)                // WRONG
    : ParentClass() {

    ```
    
    Note: You can use `ALT+ENTER` shortcut to put parameters on separate lines. 

* A single blank line should be added before annotations.

    ```
    @BindView(R.id.buttonSave)
    var buttonSave

    @BindView(R.id.buttonCancel)
    var buttonCancel
    ```

###### Comments

* When writing multi-line comments, use the /* ... */ style

    ```
    /*
    * Use this for comments                          // Use this for comments up to two lines
    * that are more than two-lines.
    */
    ```

