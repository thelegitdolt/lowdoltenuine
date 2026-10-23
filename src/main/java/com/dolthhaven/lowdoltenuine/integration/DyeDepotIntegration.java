package com.dolthhaven.lowdoltenuine.integration;

import com.dolthhaven.lowdoltenuine.utils.Consts;
import com.dolthhaven.lowdoltenuine.utils.RegUtil;
import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DyeDepotIntegration {
    public static final Supplier<List<ResourceLocation>> DYES = Suppliers.memoize(() -> Util.make(new ArrayList<>(), list -> {
        Arrays.stream(DyeDepotDyes.values()).forEach(dyecolor -> list.add(Consts.DYE_DEPOT.rl(dyecolor.name)));
        Arrays.stream(VanillaDyes.values()).forEach(dyecolor -> list.add(Consts.MINECRAFT.rl(dyecolor.name)));
    }));

    private static final Pattern DYE_PATTERN = Pattern.compile("([a-z]+)_dye$");

    public static String getDyeDepotDyeColor(Item item) {
        ResourceLocation loc = RegUtil.itemId(item);
        if (loc.getNamespace().equals("dye_depot")) {
            Matcher matcher = DYE_PATTERN.matcher(loc.getPath());
            if (matcher.matches()) {
                String dye = matcher.group(1);
                return getDyeDepotDye(dye);
            }
        }
        return null;
    }

    public static String getDyeDepotDye(String dye) {
        try {
            DyeDepotDyes.valueOf(dye.toUpperCase(Locale.ROOT));
            return dye;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String getVanillaDye(String dye) {
        try {
            VanillaDyes.valueOf(dye.toUpperCase(Locale.ROOT));
            return dye;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String getDyeFromName(String dye) {
        if (getVanillaDye(dye) == null) {
            if (getDyeDepotDye(dye) == null) {
                return null;
            }
        }  return dye;
    }

    public enum DyeDepotDyes implements StringRepresentable {
        ROSE("rose"),
        MAROON("maroon"),
        GINGER("ginger"),
        TAN("tan"),
        BEIGE("beige"),
        CORAL("coral"),
        OLIVE("olive"),
        FOREST("forest"),
        VERDANT("verdant"),
        AMBER("amber"),
        TEAL("teal"),
        MINT("mint"),
        AQUA("aqua"),
        SLATE("slate"),
        NAVY("navy"),
        INDIGO("indigo");

        final String name;
        DyeDepotDyes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public enum VanillaDyes implements StringRepresentable {
        WHITE("white"),
        ORANGE("orange"),
        MAGENTA("magenta"),
        LIGHT_BLUE("light_blue"),
        YELLOW("yellow"),
        LIME("lime"),
        PINK("pink"),
        GRAY("gray"),
        LIGHT_GRAY("light_gray"),
        CYAN("cyan"),
        PURPLE("purple"),
        BLUE("blue"),
        BROWN("brown"),
        GREEN("green"),
        RED("red"),
        BLACK("black");

        final String name;
        VanillaDyes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
