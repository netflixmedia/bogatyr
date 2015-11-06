

## Antialiasing ##
To enable font antialiasing in Swing under Java 1.6, you have to add the following VM parameters:
```
-Dawt.useSystemAAFontSettings=on
```

## Global F1 (Help) ##
F1 key usually displays help in any context. The Swing action model would require that you set a key listener for all components on the screen. If you use input maps, you can install a central listener for all components inside a frame. This code installs a listener for F1 for all components in top-level JComponent (you cannot use this with a a JFrame, but can attach actions to the content panel of a JFrame).
```
KeyStroke ks=KeyStroke.getKeyStroke(KeyEvent.VK_F1,0);

yourTopComponent.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(ks,"HELP");
yourTopComponent.getActionMap().put("HELP",new AbstractAction(){
   public void actionPerformed(ActionEvent evt){
         // do something here, display a dialog or whatever
});
```

## Arrays as String ##
Since JDK 5, you can simply convert an array to a String:
```
Arrays.deepToString(myObjectArray);
```