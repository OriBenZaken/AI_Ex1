/**
 * Created by אורי on 11/11/2018.
 */
public interface IState<P, O> {
    P getBoard();
    IState getParent();
    O getOriginOpertaor();
}
