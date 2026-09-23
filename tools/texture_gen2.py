from PIL import Image
import re, os

def get_palette_data(path = "C:\\Users\\jacec\\Desktop\\another_furniture\\tools\\palettes\\planks", template_name = "oak_planks"):
    img = Image.open(path + "\\" + template_name + ".png").convert(mode="RGBA",dither=Image.Dither.NONE)
    img_loaded = img.load()

    img_size = img.size

    palette_data = []
    for y in range(img_size[1]):
        for x in range(img_size[0]):
            add = True
            for palette_data_entry in palette_data:
                if palette_data_entry["color"] == img_loaded[x,y] or img_loaded[x,y][3] == 0:
                    add = False
            if add:
                palette_data.append({"x":x,"y":y,"color":img_loaded[x,y]})

    print(palette_data)
    return palette_data

def generate_wood_types(plank_path = "C:\\Users\\jacec\\Desktop\\modding\\another_furniture\\tools\\palettes\\planks", template_path = "C:\\Users\\jacec\\Desktop\\modding\\another_furniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block", banned_variants = ["shutter"]):
    wood_types = ["oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "crimson", "warped", "bamboo", "cherry", "pale_oak"]

    

    planks = [os.path.join(dp, f) for dp, dn, filenames in os.walk(plank_path) for f in filenames if os.path.splitext(f)[1] == '.png']
    templates = [os.path.join(dp, f) for dp, dn, filenames in os.walk(template_path) for f in filenames if (os.path.splitext(f)[1] == '.png' and os.path.splitext(f)[0].startswith("oak"))]
    print(templates)
    
    primary_palette_data = get_palette_data(plank_path, "oak_planks")
    
    for plank in planks:
        plank_nopng = plank.replace(".png", "")
        palette_noload = Image.open(plank).convert("RGBA",dither=Image.Dither.NONE,colors=7)
        palette = palette_noload.load()
        palette_size = palette_noload.size
        
        secondary_palette_data = []
        print(plank)
        for primary_palette_data_item in primary_palette_data:
            x = primary_palette_data_item["x"]
            y = primary_palette_data_item["y"]
            secondary_palette_data.append({"x":x,"y":y,"color":palette[x,y]})
        print(secondary_palette_data)
        
        for template in templates:
            is_valid_template = True
            for banned_variant in banned_variants:
                if re.search(banned_variant, template):
                    is_valid_template = False
                    
            if not is_valid_template:
                continue
            
            input_img = Image.open(template).convert(mode="RGBA",dither=Image.Dither.NONE)
            input_img_loaded = input_img.load()

            # skip swapping colors if it's oak
            if not plank.split("\\")[-1].startswith("oak"):
                input_img_size = input_img.size
                img_new = input_img
                image_new = img_new.load()
                
                size = input_img.size
                for y in range(size[1]):
                    for x in range(size[0]):

                        
                        pix = input_img_loaded[x,y]
                        for num, primary_palette_data_item in enumerate(primary_palette_data):
                            if pix == primary_palette_data_item["color"]:
                                image_new[x,y] = secondary_palette_data[num]["color"]
                            elif pix == (0, 0, 0, 0):
                                image_new[x,y] = (0, 0, 0, 0)
            else:
                img_new = input_img
                
            save_path = template.replace("oak", plank.split("\\")[-1].replace(".png","")).replace("_planks","")
            img_new.save(save_path)
            print(f"saved {save_path}")


def generate_color_types(color_path = "C:\\Users\\jacec\\Desktop\\another_furniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block\\curtain",
                         template_path = "C:\\Users\\jacec\\Desktop\\another_furniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block",
                         banned_variants = ["curtain", "sofa_old", "tablecloth"]):
    color = ["white","orange","magenta","light_blue","yellow","lime","pink","gray","light_gray","cyan","purple","blue","brown","green","red","black"]

    

    colors = [os.path.join(dp, f) for dp, dn, filenames in os.walk(color_path) for f in filenames if os.path.splitext(f)[1] == '.png' and os.path.splitext(f)[0] in color]
    templates = [os.path.join(dp, f) for dp, dn, filenames in os.walk(template_path) for f in filenames if (os.path.splitext(f)[1] == '.png' and os.path.splitext(f)[0].startswith("white"))]
    
    primary_palette_data = get_palette_data(color_path, "white")
    
    for color in colors:
        print(color)
        plank_nopng = color.replace(".png", "")
        palette_noload = Image.open(color).convert("RGBA",dither=Image.Dither.NONE,colors=7)
        palette = palette_noload.load()
        palette_size = palette_noload.size
        
        secondary_palette_data = []
        
        for primary_palette_data_item in primary_palette_data:
            x = primary_palette_data_item["x"]
            y = primary_palette_data_item["y"]
            secondary_palette_data.append({"x":x,"y":y,"color":palette[x,y]})
        print(secondary_palette_data)

        for template in templates:
            is_valid_template = True
            for banned_variant in banned_variants:
                if banned_variant in template and not "curtain2" in template:
                    is_valid_template = False
                    
            if not is_valid_template:
                continue
            
            input_img = Image.open(template).convert(mode="RGBA",dither=Image.Dither.NONE)
            input_img_loaded = input_img.load()

            # skip swapping colors if it's oak
            if not color.split("\\")[-1].startswith("white"):
                input_img_size = input_img.size
                img_new = Image.new("RGBA", (input_img_size[0], input_img_size[1]), color = "white")
                image_new = img_new.load()
                
                size = input_img.size
                
                for y in range(size[1]):
                    for x in range(size[0]):
                        found = False
                        pix = input_img_loaded[x,y]
                        for num, primary_palette_data_item in enumerate(primary_palette_data):
                            if not found:
                                if pix == primary_palette_data_item["color"]:
                                    image_new[x,y] = secondary_palette_data[num]["color"]
                                    found = True
                                elif pix == (0, 0, 0, 0):
                                    image_new[x,y] = (0, 0, 0, 0)
                                    found = True
                                else:
                                    image_new[x,y] = input_img_loaded[x,y]

            else:
                img_new = input_img
                
            save_path = template.replace("white", color.split("\\")[-1].replace(".png","")).replace("_planks","")
            if save_path.endswith("_.png"):
                save_path = save_path[:-5] + ".png"
            img_new.save(save_path)
            print(f"saved {save_path}")

##generate_color_types(
##    "C:\\Users\\jacec\\Desktop\\another_furniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block\\curtain",
##    "D:\\desktop\\files\\starfish_studios\\packs\\afm-addon\\packs\\resource_packs\\0\\textures\\sf\\afm\\items",
##)



generate_wood_types(banned_variants = ["shutter", "drawer", "unused"])

#generate_color_types()
#    "C:\\Users\\lukeh\\Desktop\\Starfish-Modding\\AnotherFurniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block\\curtain",
#    "C:\\Users\\lukeh\\Desktop\\Starfish-Modding\\AnotherFurniture\\common\\src\\main\\resources\\assets\\another_furniture\\textures\\block",
