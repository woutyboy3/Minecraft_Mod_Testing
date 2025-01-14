package net.minecraft.network.status.client;

import java.io.IOException;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.IServerStatusNetHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CPingPacket implements IPacket<IServerStatusNetHandler> {
   private long clientTime;

   public CPingPacket() {
   }

   @OnlyIn(Dist.CLIENT)
   public CPingPacket(long clientTimeIn) {
      this.clientTime = clientTimeIn;
   }

   /**
    * Reads the raw packet data from the data stream.
    */
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.clientTime = buf.readLong();
   }

   /**
    * Writes the raw packet data to the data stream.
    */
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeLong(this.clientTime);
   }

   public void processPacket(IServerStatusNetHandler handler) {
      handler.processPing(this);
   }

   public long getClientTime() {
      return this.clientTime;
   }
}