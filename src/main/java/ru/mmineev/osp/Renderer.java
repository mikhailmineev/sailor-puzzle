package ru.mmineev.osp;

public interface Renderer {

    void render(Field field);

    void render(Action action, int back);

}