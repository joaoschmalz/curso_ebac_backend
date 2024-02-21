package org.example.geometrics;

/**
 * @author joaoschmalz
 */
public class Pyramid
{
  private double height;
  private double baseHeight;
  private double baseWidth;

  public double getHeight()
  {
    return height;
  }

  public void setHeight(double height)
  {
    this.height = height;
  }

  public double getBaseHeight()
  {
    return baseHeight;
  }

  public void setBaseHeight(double baseHeight)
  {
    this.baseHeight = baseHeight;
  }

  public double getBaseWidth()
  {
    return baseWidth;
  }

  public void setBaseWidth(double baseWidth)
  {
    this.baseWidth = baseWidth;
  }

  /**
   * Calculate pyramid volume
   *
   * @return calculated volume for the given pyramid
   */
  public double getVolume()
  {
    return ((this.baseHeight * this.baseWidth) * this.height) / 3;
  }
}