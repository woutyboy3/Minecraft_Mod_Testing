package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SPlayerListHeaderFooterPacket implements IPacket<IClientPlayNetHandler> {
   private ITextComponent header;
   private ITextComponent footer;

   /**
    * Reads the raw packet data from the data stream.
    */
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.header = buf.readTextComponent();
      this.footer = buf.readTextComponent();
   }

   /**
    * Writes the raw packet data to the data stream.
    */
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeTextComponent(this.header);
      buf.writeTextComponent(this.footer);
   }

   public void processPacket(IClientPlayNetHandler handler) {
      handler.handlePlayerListHeaderFooter(this);
   }

   @OnlyIn(Dist.CLIENT)
   public ITextComponent getHeader() {
      return this.header;
   }

   @OnlyIn(Dist.CLIENT)
   public ITextComponent getFooter() {
      return this.footer;
   }
}