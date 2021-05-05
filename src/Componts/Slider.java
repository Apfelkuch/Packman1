package Componts;

import Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Slider extends Object {

    Handler handler;

    // graphic data
    private Color color = Color.BLACK;

    private Rectangle sliderRect;

    private int sliderX;
    private int sliderWidth;
    public int defaultSliderWidth = 10;

    private int cornerRounds = 5;

    private final int sideDistance = 10;
    private int sideDistanceRight = sideDistance;
    private int sideDistanceLeft = sideDistance;

    private float value;
    private int range = 1;

    private Point prevPoint;

    private String text;
    private Color textColor = Color.BLACK;
    private Font textFont;

    public Slider(Handler handler, String text, float startInPercent, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.handler = handler;
        this.text = text;
        this.value = startInPercent;
        this.sliderX = x + width / 3;
        this.sliderWidth = width / 3 * 2;
        sliderRect = new Rectangle((int) (sliderX + sideDistanceLeft + (value * (sliderWidth - sideDistanceLeft - sideDistanceRight)) / range), y, defaultSliderWidth, height);
        // X = offset + value * (possible space / range)
    }

    @Override
    public void render(Graphics g) {
//        {
//            // box
//            g.setColor(Color.RED);
//            g.drawRect(x, y, width, height);
//        }
        // text
        g.setColor(textColor);
        g.setFont(textFont);
        int stringWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, this.x + (this.width - sliderWidth) / 2 - (stringWidth / 2), this.y + (this.height / 2) + (textFont.getSize() / 3));

        // rail
        g.setColor(color);
        g.fillRoundRect(sliderX, y + (height / 2) - 1, sliderWidth, 2,1,1);
        // slider
        g.setColor(color);
        g.fillOval(sliderRect.x, sliderRect.y, sliderRect.width, sliderRect.height);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(intersect(sliderRect, e.getPoint())) {
            prevPoint = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        prevPoint = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(prevPoint != null) {
            Point currentPoint = e.getPoint();
            sliderRect.translate(
                    (int) (currentPoint.getX() - prevPoint.getX()),
                    0
            );
            // slider position correction
            if(sliderRect.x < (sliderX + sideDistanceLeft - (sliderRect.width) / 2)) { // constrain left
                sliderRect.x = (sliderX + sideDistanceLeft - (sliderRect.width) / 2);
            } else if(sliderRect.x > (sliderX + sliderWidth - sideDistanceRight - (sliderRect.width) / 2)) { // constrain right
                sliderRect.x = (sliderX + sliderWidth - sideDistanceRight - (sliderRect.width) / 2);
            } else { // if the slider must not be corrected reset drag motion.
                prevPoint = currentPoint;
            }
            // set the value
            float a = (sliderRect.x - this.sliderX - sideDistanceLeft + sliderRect.width / 2);
            float b = this.sliderWidth - (sideDistanceLeft + sideDistanceRight);
            value = a / b * this.range;
        }
    }

    /**
     * Arrange the "value" between start and end return this value.
     * If start and end are equal, the result is the start parameter.
     * start < end, otherwise the result is always 0.
     * The methode only works if the range is 1, otherwise the result is always 0.
     * The "value" variable is not changed.
     * @param start The start parameter.
     * @param end The end parameter.
     * @return the modified "value".
     */
    public float arrangeBetween(float start, float end) {
        if(range != 1) return 0;
        if(start > end) return 0;
        if(start == end) return start;
        float diff = end - start;
        return start + (diff * value);
    }

    // GETTER && SETTER
    public float getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }
    public int getCornerRounds() {
        return cornerRounds;
    }
    public int getRange() {
        return range;
    }
    public int getDefaultSideDistance() {
        return sideDistance;
    }
    public int getSideDistanceLeft() {
        return sideDistanceLeft;
    }
    public int getSideDistanceRight() {
        return sideDistanceRight;
    }

    private void setSliderRect(int x, int y, int width, int height) {
        if(sliderRect == null) {
            sliderRect = new Rectangle(x, y, width, height);
        } else {
            sliderRect.setBounds(x, y, width, height);
        }
    }
    public void setCornerRounds(int cornerRounds) {
        this.cornerRounds = cornerRounds;
    }
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        this.sliderX = x + width / 3;
        this.sliderWidth = width / 3 * 2;
    }
    public void setValue(float value) {
        this.value = value;
        setSliderRect((int) (sliderX + sideDistanceLeft - (defaultSliderWidth / 2) + (value * (sliderWidth - sideDistanceLeft - sideDistanceRight)) / range), y,defaultSliderWidth,height);
    }
    public void setRange(int range) {
        this.range = range;
        setSliderRect((int) (sliderX + sideDistanceLeft + (value * (sliderWidth - sideDistanceLeft - sideDistanceRight)) / range), y,defaultSliderWidth,height);
    }
    public void setSideDistanceRight(int sideDistanceRight) {
        this.sideDistanceRight = sideDistanceRight;
        setSliderRect((int) (sliderX + sideDistanceLeft + (value * (sliderWidth - sideDistanceLeft - sideDistanceRight)) / range), y,defaultSliderWidth,height);
    }
    public void setSideDistanceLeft(int sideDistanceLeft) {
        this.sideDistanceLeft = sideDistanceLeft;
        setSliderRect((int) (sliderX + sideDistanceLeft + (value * (sliderWidth - sideDistanceLeft - sideDistanceRight)) / range), y,defaultSliderWidth,height);
    }
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        setSliderRect((int) (sliderX + sideDistanceLeft + (value * (sliderWidth - sideDistanceLeft - sideDistanceRight)) / range), y,defaultSliderWidth,height);
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public void setTextFont(Font textFont) {
        this.textFont = textFont;
    }
}
