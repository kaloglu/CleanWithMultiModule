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
    ```

* Fragments:

    ```
    fragment_register           // CORRECT
    ```

* Items:

    ```
    recycler_item_bulletin      // CORRECT
    ```

* Components:

    ```
    view_event_item             // CORRECT
    ```

* Dialogs: Dialogs names should contain `dialog_` prefix

    ```
    dialog_login.xml
    ```

* Drawables:

    ```
    ic_launcher.png        // CORRECT
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
    ```

###### Naming

* Classes:
Class names should contain type as suffix
    ```
    LoginActivity
    LoginFragment
    ```

* Fields:
    ```
    String nameAnotherWords;          // CORRECT
    ```

* Constants:
Constant names use CONSTANT_CASE: all uppercase letters, with words separated by underscores, declared static final.
    ```
    static final int NUMBER_CODE = 5;
    ```

* Parameters:
One-character parameter names should be avoided.
    ```
    public void setName(String name) {...}      // CORRECT
    ```

* Packages:
Package names are all lowercase, with consecutive words simply concatenated together (no underscores).
    ```
    com.kaloglu.boiler_plate.boilerplate            // CORRECT
    ```

* Identifier:
Camelcase pattern should be used: `@+id/${viewType}${logicalName}`

    ```
    @+id/textViewName
    @+id/buttonSubmit
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
           android:name=".ui.register.LoginActivity"/>                 // CORRECT
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

    ```
    
    Note: You can use `ALT+ENTER` shortcut to put parameters on separate lines. 

###### Comments

* When writing multi-line comments, use the /* ... */ style

    ```
    /*
    * Use this for comments                          // Use this for comments up to two lines
    * that are more than two-lines.
    */
    ```

