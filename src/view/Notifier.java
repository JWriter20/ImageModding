package view;

import controller.Notifiable;

/**
 * Interface used to create Notifiable objects.
 */
public interface Notifier {

  /**
   * Sets the Notifiable object to the given one.
   * @param v The given object
   */
  void setNotifiable(Notifiable v);
}
