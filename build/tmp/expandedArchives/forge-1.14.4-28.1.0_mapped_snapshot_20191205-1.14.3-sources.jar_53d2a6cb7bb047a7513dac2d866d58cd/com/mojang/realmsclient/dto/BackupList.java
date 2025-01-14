package com.mojang.realmsclient.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class BackupList extends ValueObject {
   private static final Logger LOGGER = LogManager.getLogger();
   public List<Backup> backups;

   public static BackupList parse(String p_parse_0_) {
      JsonParser jsonparser = new JsonParser();
      BackupList backuplist = new BackupList();
      backuplist.backups = new ArrayList<>();

      try {
         JsonElement jsonelement = jsonparser.parse(p_parse_0_).getAsJsonObject().get("backups");
         if (jsonelement.isJsonArray()) {
            Iterator<JsonElement> iterator = jsonelement.getAsJsonArray().iterator();

            while(iterator.hasNext()) {
               backuplist.backups.add(Backup.parse(iterator.next()));
            }
         }
      } catch (Exception exception) {
         LOGGER.error("Could not parse BackupList: " + exception.getMessage());
      }

      return backuplist;
   }
}