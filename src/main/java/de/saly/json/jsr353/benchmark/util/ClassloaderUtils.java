package de.saly.json.jsr353.benchmark.util;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public final class ClassloaderUtils {

    private ClassloaderUtils() {

    }

    private static final Class<?>[] parameters = new Class[] { URL.class };

    public static File[] loadJarsFromDir(final File dir) throws Exception {

        final File[] jars = dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {

                return name.endsWith(".jar");
            }
        });

        for (int i = 0; i < jars.length; i++) {
            final JarFile file = new JarFile(jars[i]);
            final Enumeration<JarEntry> e = file.entries();
            final Manifest mf = file.getManifest(); // If the jar has a class-path in it's manifest add it's entries
            final List<URL> urls = new ArrayList<URL>();
            urls.add(new URL("jar:file:" + jars[i].getAbsolutePath() + "!/"));
            System.out.println("Jar File: " + jars[i]);

            if (mf != null) {
                final String cp = mf.getMainAttributes().getValue("class-path");
                if (cp != null) {
                    for (final String cpe : cp.split("\\s+")) {
                        final File lib = new File(jars[i].getParentFile(), cpe);
                        urls.add(lib.toURI().toURL());
                        System.out.println("    class-path: " + lib);
                    }
                }
            }

            final URL[] urlsArray = urls.toArray(new URL[0]);

            final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            final Method method = URLClassLoader.class.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, urlsArray);
            final ClassLoader cl = new URLClassLoader(urlsArray, sysloader);

            while (e.hasMoreElements()) {
                final JarEntry je = e.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }

                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                Class.forName(className, true, cl);
                System.out.println("    Class: " + className);
            }

        }

        
        return jars;
    }

}
