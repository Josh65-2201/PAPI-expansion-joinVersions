package com.joinVersions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.geysermc.geyser.api.GeyserApi;
import org.geysermc.geyser.api.util.MinecraftVersion;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public class placeholders extends PlaceholderExpansion {
    @Override
    public String getAuthor(){
        return "Josh65";
    }

    @Override
    public String getIdentifier() {
        return "joinversions";
    }

    @Override
    public String getVersion(){
        return "1.1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String params){
        if (params.equals("geyserProtocols")) {
            List<Integer> protocolStrings = GeyserApi.api().supportedBedrockVersions().stream().map(MinecraftVersion::protocolVersion).collect(Collectors.toList());
            return String.join(", ", protocolStrings.toString().replace("[", "").replace("]", ""));
        }

        if (params.equals("geyser")) {
            List<String> versionStrings = GeyserApi.api().supportedBedrockVersions().stream().map(MinecraftVersion::versionString).collect(Collectors.toList());
            return String.join(", ", versionStrings);
        }

        if (params.equals("viaProtocols")) {
            List<Integer> supportedVersions = new ArrayList<>(Via.getAPI().getSupportedVersions());
            return supportedVersions.toString().replace("[", "").replace("]", "");
        }

        if (params.equals("via")) {
            List<Integer> supportedVersions = new ArrayList<>(Via.getAPI().getSupportedVersions());
            List<String> versionStrings = supportedVersions.stream().map(protocol -> ProtocolVersion.getProtocol(protocol).getName()).collect(Collectors.toList());
            return String.join(", ", versionStrings);
        }
        
        return null;
    }
}