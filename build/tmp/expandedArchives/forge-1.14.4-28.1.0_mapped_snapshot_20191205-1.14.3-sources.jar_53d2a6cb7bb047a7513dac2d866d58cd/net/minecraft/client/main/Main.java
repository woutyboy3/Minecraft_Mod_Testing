package net.minecraft.client.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.properties.PropertyMap.Serializer;
import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.List;
import java.util.OptionalInt;
import javax.annotation.Nullable;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.client.GameConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenSize;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.DefaultUncaughtExceptionHandler;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.Session;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class Main {
   private static final Logger LOGGER = LogManager.getLogger();

   public static void main(String[] p_main_0_) {
      OptionParser optionparser = new OptionParser();
      optionparser.allowsUnrecognizedOptions();
      optionparser.accepts("demo");
      optionparser.accepts("fullscreen");
      optionparser.accepts("checkGlErrors");
      OptionSpec<String> optionspec = optionparser.accepts("server").withRequiredArg();
      OptionSpec<Integer> optionspec1 = optionparser.accepts("port").withRequiredArg().ofType(Integer.class).defaultsTo(25565);
      OptionSpec<File> optionspec2 = optionparser.accepts("gameDir").withRequiredArg().ofType(File.class).defaultsTo(new File("."));
      OptionSpec<File> optionspec3 = optionparser.accepts("assetsDir").withRequiredArg().ofType(File.class);
      OptionSpec<File> optionspec4 = optionparser.accepts("resourcePackDir").withRequiredArg().ofType(File.class);
      OptionSpec<String> optionspec5 = optionparser.accepts("proxyHost").withRequiredArg();
      OptionSpec<Integer> optionspec6 = optionparser.accepts("proxyPort").withRequiredArg().defaultsTo("8080").ofType(Integer.class);
      OptionSpec<String> optionspec7 = optionparser.accepts("proxyUser").withRequiredArg();
      OptionSpec<String> optionspec8 = optionparser.accepts("proxyPass").withRequiredArg();
      OptionSpec<String> optionspec9 = optionparser.accepts("username").withRequiredArg().defaultsTo("Player" + Util.milliTime() % 1000L);
      OptionSpec<String> optionspec10 = optionparser.accepts("uuid").withRequiredArg();
      OptionSpec<String> optionspec11 = optionparser.accepts("accessToken").withRequiredArg().required();
      OptionSpec<String> optionspec12 = optionparser.accepts("version").withRequiredArg().required();
      OptionSpec<Integer> optionspec13 = optionparser.accepts("width").withRequiredArg().ofType(Integer.class).defaultsTo(854);
      OptionSpec<Integer> optionspec14 = optionparser.accepts("height").withRequiredArg().ofType(Integer.class).defaultsTo(480);
      OptionSpec<Integer> optionspec15 = optionparser.accepts("fullscreenWidth").withRequiredArg().ofType(Integer.class);
      OptionSpec<Integer> optionspec16 = optionparser.accepts("fullscreenHeight").withRequiredArg().ofType(Integer.class);
      OptionSpec<String> optionspec17 = optionparser.accepts("userProperties").withRequiredArg().defaultsTo("{}");
      OptionSpec<String> optionspec18 = optionparser.accepts("profileProperties").withRequiredArg().defaultsTo("{}");
      OptionSpec<String> optionspec19 = optionparser.accepts("assetIndex").withRequiredArg();
      OptionSpec<String> optionspec20 = optionparser.accepts("userType").withRequiredArg().defaultsTo("legacy");
      OptionSpec<String> optionspec21 = optionparser.accepts("versionType").withRequiredArg().defaultsTo("release");
      OptionSpec<String> optionspec22 = optionparser.nonOptions();
      OptionSet optionset = optionparser.parse(p_main_0_);
      List<String> list = optionset.valuesOf(optionspec22);
      if (!list.isEmpty()) {
         System.out.println("Completely ignored arguments: " + list);
      }

      String s = getValue(optionset, optionspec5);
      Proxy proxy = Proxy.NO_PROXY;
      if (s != null) {
         try {
            proxy = new Proxy(Type.SOCKS, new InetSocketAddress(s, getValue(optionset, optionspec6)));
         } catch (Exception var52) {
            ;
         }
      }

      final String s1 = getValue(optionset, optionspec7);
      final String s2 = getValue(optionset, optionspec8);
      if (!proxy.equals(Proxy.NO_PROXY) && isNotEmpty(s1) && isNotEmpty(s2)) {
         Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(s1, s2.toCharArray());
            }
         });
      }

      int i = getValue(optionset, optionspec13);
      int j = getValue(optionset, optionspec14);
      OptionalInt optionalint = func_224732_a(getValue(optionset, optionspec15));
      OptionalInt optionalint1 = func_224732_a(getValue(optionset, optionspec16));
      boolean flag = optionset.has("fullscreen");
      boolean flag1 = optionset.has("demo");
      String s3 = getValue(optionset, optionspec12);
      Gson gson = (new GsonBuilder()).registerTypeAdapter(PropertyMap.class, new Serializer()).create();
      PropertyMap propertymap = JSONUtils.fromJson(gson, getValue(optionset, optionspec17), PropertyMap.class);
      PropertyMap propertymap1 = JSONUtils.fromJson(gson, getValue(optionset, optionspec18), PropertyMap.class);
      String s4 = getValue(optionset, optionspec21);
      File file1 = getValue(optionset, optionspec2);
      File file2 = optionset.has(optionspec3) ? getValue(optionset, optionspec3) : new File(file1, "assets/");
      File file3 = optionset.has(optionspec4) ? getValue(optionset, optionspec4) : new File(file1, "resourcepacks/");
      String s5 = optionset.has(optionspec10) ? optionspec10.value(optionset) : PlayerEntity.getOfflineUUID(optionspec9.value(optionset)).toString();
      String s6 = optionset.has(optionspec19) ? optionspec19.value(optionset) : null;
      String s7 = getValue(optionset, optionspec);
      Integer integer = getValue(optionset, optionspec1);
      Session session = new Session(optionspec9.value(optionset), s5, optionspec11.value(optionset), optionspec20.value(optionset));
      GameConfiguration gameconfiguration = new GameConfiguration(new GameConfiguration.UserInformation(session, propertymap, propertymap1, proxy), new ScreenSize(i, j, optionalint, optionalint1, flag), new GameConfiguration.FolderInformation(file1, file3, file2, s6), new GameConfiguration.GameInformation(flag1, s3, s4), new GameConfiguration.ServerInformation(s7, integer));
      Thread thread = new Thread("Client Shutdown Thread") {
         public void run() {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft != null) {
               IntegratedServer integratedserver = minecraft.getIntegratedServer();
               if (integratedserver != null) {
                  integratedserver.initiateShutdown(true);
               }

            }
         }
      };
      thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
      Runtime.getRuntime().addShutdownHook(thread);
      Thread.currentThread().setName("Client thread");
      (new Minecraft(gameconfiguration)).run();
   }

   private static OptionalInt func_224732_a(@Nullable Integer p_224732_0_) {
      return p_224732_0_ != null ? OptionalInt.of(p_224732_0_) : OptionalInt.empty();
   }

   /**
    * Gets the value of a specified command-line parameter from an OptionSet. If it doesn't exist, it returns the
    * default value for the parameter.
    */
   private static <T> T getValue(OptionSet set, OptionSpec<T> option) {
      try {
         return set.valueOf(option);
      } catch (Throwable throwable) {
         if (option instanceof ArgumentAcceptingOptionSpec) {
            ArgumentAcceptingOptionSpec<T> argumentacceptingoptionspec = (ArgumentAcceptingOptionSpec)option;
            List<T> list = argumentacceptingoptionspec.defaultValues();
            if (!list.isEmpty()) {
               return list.get(0);
            }
         }

         throw throwable;
      }
   }

   /**
    * Returns true if the given string is neither null nor empty.
    */
   private static boolean isNotEmpty(String str) {
      return str != null && !str.isEmpty();
   }

   static {
      System.setProperty("java.awt.headless", "true");
   }
}