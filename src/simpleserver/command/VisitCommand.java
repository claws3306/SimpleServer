/*
 * Copyright (c) 2010 SimpleServer authors (see CONTRIBUTORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package simpleserver.command;

import simpleserver.Color;
import simpleserver.Player;

public class VisitCommand extends OnlinePlayerArgCommand {
  public VisitCommand() {
    super("visit PLAYER", "Teleport to the player if he agrees with ok");
  }

  @Override
  protected void executeWithTarget(Player player, String message, Player target) {
    if (player.getName().equals(target.getName())) {
      player.addTMessage(Color.RED, "You can not visit yourself!");
      return;
    }

    if (player.getDimension() == target.getDimension()) {
      player.addTMessage(Color.GRAY, "Requesting %s for a visit...", target.getName());
      player.addTMessage(Color.GRAY, "If nothing happens within 10 seconds, your request is denied.");

      target.addTMessage(Color.GRAY, "%s would like to visit you. If you agree,", player.getName());
      target.addTMessage(Color.GRAY, "issue the command %s within 10 seconds.", commandPrefix() + "ok");

      target.addVisitRequest(player);
    } else {
      player.addTMessage(Color.RED, "You and %s are in different dimensions.", target.getName());
      player.addTMessage(Color.RED, "No teleport possible!");
    }
  }
}
