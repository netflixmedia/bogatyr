

## Heap Size ##

| **JVM** **option** | **Description** |
|:-------------------|:----------------|
|-Xmssize in bytes   |sets the initial size of the Java heap (default 2MB). The values must be a multiple of, and greater than, 1024 bytes (1KB). (-server flag increases the default size to 32MB)|
|-Xmnsize in bytes   |sets the initial Java heap size for the Eden generation (default value is 640KB). (-server flag increases the default size to 2MB)|
|-Xmxsize in bytes   |sets the maximum size to which the Java heap can grow (default size is 64MB). (-server flag increases the default size to 128MB).|
It is good practice with server-side Java applications to set the minimum -Xms and maximum -Xmx heap sizes to the same value.
The values are entered normally in megabytes, e.g. -Xmx512m (=512 MB maximum Java heap size).

**Important:**<br>
If you monitor your Java process with an OS tool like top or taskmanager, you may see the amount of memory you use exceed the amount you have specified for -Xmx. -Xmx limits the Java heap size, Java will allocate memory for other things, including a stack for each thread. It is not unusual for the total memory consumption of the VM to exceed the value of -Xmx.<br>
<br>
Example:<br>
<code>java -Xms128m -Xmx512m</code>

<h2>Stack Size</h2>

<table><thead><th> <b>JVM</b> <b>option</b> </th><th> <b>Description</b> </th></thead><tbody>
<tr><td>-Xsssize in bytes         </td><td>sets the size of the Java stack (default is 512KB).</td></tr></tbody></table>

Each thread in the VM get's a stack. The stack size will limit the number of threads that you can have. If you get a <a href='http://download.oracle.com/javase/6/docs/api/java/lang/StackOverflowError.html'>StackOverflowError</a> increase the value. 2048k is an appropriate value for most situations<br>
<br>
Example:<br>
<code>java -Xss2048k</code>