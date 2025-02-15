package org.bmk.ccrares.util;

import dev.esophose.playerparticles.api.PlayerParticlesAPI;
import dev.esophose.playerparticles.particles.ParticleEffect;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ParticleManager {
    // API for PlayerParticles
    private PlayerParticlesAPI ppApi;


    public ParticleManager() {
        // Hook into PlayerParticles API
        if(Bukkit.getPluginManager().isPluginEnabled("PlayerParticles")) {
            ppApi = PlayerParticlesAPI.getInstance();
        }
    }

    // Enable particle effect for player (Used when player equips item)
    public void setParticle(Player p, ParticleEffect effect, ParticleStyle style) {
        // Remove any active particles first to make sure we dont add duplicates
        ppApi.resetActivePlayerParticles(p);

        // Add particle
        ppApi.addActivePlayerParticle(p, effect, style);
    }

    // Remove particles (Used when player removes item)
    public void removeParticles(Player p) {
        //ppApi.removeActivePlayerParticle(p, 0);
        ppApi.resetActivePlayerParticles(p);
    }

}
