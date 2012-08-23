// Date: 5/6/2012 12:17:18 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package pixelmon.models.pokemon;

import net.minecraft.src.*;

public class ModelGastly extends ModelBase
{
  //fields
    ModelRenderer Body1;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer righteye;
    ModelRenderer lefteye;
    ModelRenderer ghosttrailpartical1;
    ModelRenderer ghosttrailpartical2;
    ModelRenderer ghosttrailpartical3;
    ModelRenderer Mouth;
    ModelRenderer Shape1;
  
  public ModelGastly()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Body1 = new ModelRenderer(this, 0, 0);
      Body1.addBox(-3F, -3F, -3F, 6, 6, 6);
      Body1.setRotationPoint(0F, 11F, -3F);
      Body1.setTextureSize(64, 32);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 0F);
      body2 = new ModelRenderer(this, 0, 12);
      body2.addBox(-3.5F, -2.5F, -2.5F, 7, 5, 5);
      body2.setRotationPoint(0F, 11F, -3F);
      body2.setTextureSize(64, 32);
      body2.mirror = true;
      setRotation(body2, 0F, 0F, 0F);
      body3 = new ModelRenderer(this, 24, 0);
      body3.addBox(-2.5F, -3.5F, -2.5F, 5, 7, 5);
      body3.setRotationPoint(0F, 11F, -3F);
      body3.setTextureSize(64, 32);
      body3.mirror = true;
      setRotation(body3, 0F, 0F, 0F);
      body4 = new ModelRenderer(this, 40, 5);
      body4.addBox(-2.5F, -2.5F, -3.5F, 5, 5, 7);
      body4.setRotationPoint(0F, 11F, -3F);
      body4.setTextureSize(64, 32);
      body4.mirror = true;
      setRotation(body4, 0F, 0F, 0F);
      righteye = new ModelRenderer(this, 0, 25);
      righteye.addBox(-3F, -3F, 0F, 3, 3, 0);
      righteye.setRotationPoint(-0.4F, 10.5F, -6.6F);
      righteye.setTextureSize(64, 32);
      righteye.mirror = true;
      setRotation(righteye, 0F, 0F, 0.1047198F);
      lefteye = new ModelRenderer(this, 0, 22);
      lefteye.addBox(0F, -3F, 0F, 3, 3, 0);
      lefteye.setRotationPoint(0.4F, 10.5F, -6.6F);
      lefteye.setTextureSize(64, 32);
      lefteye.mirror = true;
      setRotation(lefteye, 0F, 0F, -0.1047198F);
      ghosttrailpartical1 = new ModelRenderer(this, 0, 0);
      ghosttrailpartical1.addBox(0F, 0F, 0F, 1, 1, 1);
      ghosttrailpartical1.setRotationPoint(2F, 12F, 3F);
      ghosttrailpartical1.setTextureSize(64, 32);
      ghosttrailpartical1.mirror = true;
      setRotation(ghosttrailpartical1, 0F, 0F, 0F);
      ghosttrailpartical2 = new ModelRenderer(this, 0, 0);
      ghosttrailpartical2.addBox(0F, 0F, 0F, 1, 1, 1);
      ghosttrailpartical2.setRotationPoint(-4F, 7F, -1F);
      ghosttrailpartical2.setTextureSize(64, 32);
      ghosttrailpartical2.mirror = true;
      setRotation(ghosttrailpartical2, 0F, 0F, 0F);
      ghosttrailpartical3 = new ModelRenderer(this, 0, 0);
      ghosttrailpartical3.addBox(0F, 0F, 0F, 1, 1, 1);
      ghosttrailpartical3.setRotationPoint(3F, 14F, -7F);
      ghosttrailpartical3.setTextureSize(64, 32);
      ghosttrailpartical3.mirror = true;
      setRotation(ghosttrailpartical3, 0F, 0F, 0F);
      Mouth = new ModelRenderer(this, 6, 23);
      Mouth.addBox(-2.5F, -1F, 0F, 5, 2, 0);
      Mouth.setRotationPoint(0F, 12.5F, -6.6F);
      Mouth.setTextureSize(64, 32);
      Mouth.mirror = true;
      setRotation(Mouth, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 9, 25);
      Shape1.addBox(-2F, -1F, 0F, 2, 1, 0);
      Shape1.setRotationPoint(-0.5F, 12.2F, -6.6F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0.0174533F, 0.1047198F);
      Shape1 = new ModelRenderer(this, 9, 25);
      Shape1.addBox(0F, -1F, 0F, 2, 1, 0);
      Shape1.setRotationPoint(0.5F, 12.2F, -6.6F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0.0174533F, -0.1047198F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Body1.render(f5);
    body2.render(f5);
    body3.render(f5);
    body4.render(f5);
    righteye.render(f5);
    lefteye.render(f5);
    ghosttrailpartical1.render(f5);
    ghosttrailpartical2.render(f5);
    ghosttrailpartical3.render(f5);
    Mouth.render(f5);
    Shape1.render(f5);
    Shape1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
