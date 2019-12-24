# UniversalFriends
A library that aims at having friends that are the same for each utility mod, such as [forgehax](https://github.com/fr1kin/ForgeHax) and [wwe](https://wweclient.com/). 

Critism and discussion is welcome. Join the [discord](https://discord.gg/JNjnpBu) for discussion.

## Planned:
Option to give players a different level of friendship, along with a neutral. Also enemies can be saved.

The values:
- 0 is neutral. Every player that doesn't have a value assigned to it has the value 0.
- 1 is neutral friend. When you want to use the friend system old school this is the value all your friends get.
- -1 is neutral enemy. For every friend there is an enemy. Therefor you would also want to safe who you dont like.
- the values should normally go from -2 to 2 so you have room below neutral enemy and above neutral friend. And at the same time you can say that you like someone that much that you give him the maximum friend value. This is for GUIs. With commands it should still be possible to give an arbitrary friendship value
