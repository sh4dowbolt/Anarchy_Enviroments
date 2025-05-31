package com.suraev.Adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Location;
import java.io.IOException;
import com.google.gson.stream.JsonToken;
import org.bukkit.Bukkit;
import org.bukkit.World;



public class LocationTypeAdapter extends TypeAdapter<Location> {
    
    @Override
    public void write(JsonWriter out, Location value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name("world").value(value.getWorld().getName());
        out.name("x").value(value.getX());
        out.name("y").value(value.getY());
        out.name("z").value(value.getZ());
        out.name("yaw").value(value.getYaw());
        out.name("pitch").value(value.getPitch());
        out.endObject();
    }

    @Override
    public Location read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        in.beginObject();
        String worldName = null;
        double x = 0;
        double y = 0;
        double z = 0;
        float yaw = 0;
        float pitch = 0;
        while (in.hasNext()) {
            String name = in.nextName();
            if (name.equals("world")) {
                worldName = in.nextString();
            } else if (name.equals("x")) {
                x = in.nextDouble();
            } else if (name.equals("y")) {
                y = in.nextDouble();
            } else if (name.equals("z")) {
                z = in.nextDouble();
            } else if (name.equals("yaw")) {
                yaw = (float) in.nextDouble();
            } else if (name.equals("pitch")) {
                pitch = (float) in.nextDouble();
            }
        }
        in.endObject();
        if (worldName == null) {
            throw new IOException("World name is null");
        }
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            throw new IOException("World not found: " + worldName);
        }
        return new Location(world, x, y, z, yaw, pitch);
    }
}
