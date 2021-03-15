package net.mossx.superheroes.Heroes.Powers;

import net.mossx.superheroes.Heroes.hero;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.function.Consumer;

public class SupermanPowers {
    public static void LazerEyes(Player p) {
        for (int j = 0; j < 100; j++) {
            Location particleLocation = p.getEyeLocation().add(p.getEyeLocation().getDirection().multiply(j + 1));
            particleLocation.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1, new Particle.DustOptions(Color.RED, 0.4F));
        }
    }



    public enum inventory implements hero.inv {
        Helmet(new ItemStack(Material.LEATHER_HELMET), hero.inv.setColor((LeatherArmorMeta)new ItemStack(Material.LEATHER_HELMET).getItemMeta(), Color.YELLOW), 0),
        Leggings(new ItemStack(Material.LEATHER_LEGGINGS), hero.inv.setColor((LeatherArmorMeta)new ItemStack(Material.LEATHER_LEGGINGS).getItemMeta(), Color.fromRGB(44, 180, 2)), 1),
        Boots(new ItemStack(Material.LEATHER_BOOTS), hero.inv.setColor((LeatherArmorMeta)new ItemStack(Material.LEATHER_BOOTS).getItemMeta(), Color.YELLOW), 2),

        LazerEyes(hero.createPower(Material.RED_DYE, "Lazer Eyes"), 8, SupermanPowers::LazerEyes)
        ;

        ItemStack stack; int position; Consumer<Player> run;
        inventory(ItemStack stack, int position, Consumer<Player> run) {
            this.stack = stack;
            this.position = position;
            this.run = run;
        }
        inventory(ItemStack stack, ItemMeta meta, int position) {
            meta.getPersistentDataContainer().set(invKey, PersistentDataType.DOUBLE, Math.PI);
            stack.setItemMeta(meta);
            this.stack = stack; this.position = position;
        }


        @Override
        public String getName() {
            return "Superman";
        }

        @Override
        public ItemStack getStack() {
            return stack;
        }

        @Override
        public int getPosition() {
            return position;
        }
    }
}